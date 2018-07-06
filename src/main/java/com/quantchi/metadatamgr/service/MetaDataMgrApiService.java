package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.AppProperties;
import com.quantchi.common.util;
import com.quantchi.metadatamgr.data.DSMetaInfo;
import com.quantchi.metadatamgr.data.FieldEntity;
import com.quantchi.metadatamgr.data.HiveMetaInfo;
import com.quantchi.metadatamgr.data.KeyInfo;
import com.quantchi.metadatamgr.data.entity.*;
import com.quantchi.metadatamgr.data.mapper.*;
import com.quantchi.metadatamgr.extract.HiveExtractImp;
import com.quantchi.termInfo.pojo.*;

import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetaDataMgrApiService {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrApiService.class);

    private static final String HIVETYPE = "Hive";
    private static final String HIVEMYSQLURL = "data_source_mysql_url";
    private static final String HIVEMYSQLUSER = "data_source_mysql_usr";
    private static final String HIVEMYSQLPSWD = "data_source_mysql_pswd";

    private static final int defaultSqlStart = 0;
    private static final int defaultPageSize = 10;
    private final Map<String,HiveExtractImp> g_DSInfoMap = new HashMap<>();

    @Autowired
    private  DSMetaInfoDBMapper dsMetaInfoDBMapper;

    @Autowired
    private DSTableInfoDBMapper dsTableInfoDBMapper;

    @Autowired
    private DSFieldInfoDBMapper dsFieldInfoDBMapper;

    @Autowired
    private DSFieldRelDBMapper dsFieldRelDBMapper;

    @Autowired
    private DSEntityInfoDBMapper dsEntityInfoDBMapper;

    public Map<String, Object> getDSMetaInfo(String dsName, String start, String pagesize){
        Map<String,Object> _ret = new HashMap<>();
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        if(!"".equals(dsName))
            _ex.createCriteria().andDsNameEqualTo(dsName);

        _ex.setOrderByClause("id");

        List<DSMetaInfoDB> _sqlRet = null;
        if(start != null){
            int _start = Integer.parseInt(start);
            int _pagesize = Integer.parseInt(pagesize);
            _sqlRet =  dsMetaInfoDBMapper.selectByExample(_ex,(_start-1)* _pagesize,_pagesize);
        }else
            _sqlRet =  dsMetaInfoDBMapper.selectAllByExample(_ex);

        DSMetaInfoDBExample _exCount = new DSMetaInfoDBExample();
        List<DSMetaInfoDB> _sqlRetCount = dsMetaInfoDBMapper.selectAllByExample(_exCount);
        _ret.put("data",_sqlRet);
        _ret.put("total",_sqlRetCount.size());
        return _ret;
    }

    public boolean connectTest(String host,
                                 String port,
                                 String username,
                                 String password){

        return  HiveExtractImp.connectionTest(host,port,username,password);
    }

    public boolean connectMysqlTest(String url,
                               String username,
                               String password){

        return  HiveExtractImp.connectionMysqlTest(url,username,password);
    }


    public boolean saveMetaInfo(JSONObject jsonParam) throws Exception{
        boolean _ret = false;
        String type = jsonParam.getString("data_source_type");

        DSMetaInfoDB _record = new DSMetaInfoDB();
        _record.setHost(jsonParam.getString("data_source_host"));
        _record.setPort(jsonParam.getString("data_source_port"));
        _record.setUsername(jsonParam.getString("data_source_username"));
        _record.setPassword(jsonParam.getString("data_source_passwd"));

        if(HIVETYPE.equals(type)){

            if( jsonParam.getString(HIVEMYSQLURL) == null
             || jsonParam.getString(HIVEMYSQLUSER)  == null
             || jsonParam.getString(HIVEMYSQLPSWD)  == null)
                throw new Exception("get hive meta data info error");

            _record.setHiveMetaMysqlUrl(jsonParam.getString(HIVEMYSQLURL));
            _record.setHiveMetaUsername(jsonParam.getString(HIVEMYSQLUSER));
            _record.setHiveMetaPswd(jsonParam.getString(HIVEMYSQLPSWD));

        }

        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andDsNameEqualTo(jsonParam.getString("data_source_name"));

        List<DSMetaInfoDB> _ExistItemList =  dsMetaInfoDBMapper.selectByExample(_ex,defaultSqlStart,defaultPageSize);
        if(_ExistItemList.size() > 0){
            int _activeRows = dsMetaInfoDBMapper.updateByExampleSelective(_record,_ex);
            if(_activeRows > 0)
                _ret = true;
        }else{
            _record.setDsType(jsonParam.getString("data_source_type"));
            _record.setDsName(jsonParam.getString("data_source_name"));
            int _activeRows = dsMetaInfoDBMapper.insert(_record);
            if(_activeRows > 0)
                _ret = true;
        }
        return _ret;
    }

    public boolean delMetaInfo(JSONObject json) throws Exception{
        boolean ret = false;

        String data_source_name = json.getString("data_source_name");
        String data_source_id = json.getString("data_source_id");
        if(data_source_name != null && !data_source_name.equals("")){
            DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
            dsMetaInfoDBExample.createCriteria().andDsNameEqualTo(data_source_name);
            List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);
            data_source_id = dsMetaInfoDBList.get(0).getId().toString();
        }
        //获取数据源表
        DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
        dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(data_source_id);
        List<DSTableInfoDB> dsTableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
        if(dsTableInfoDBList.size() != 0){
            for(DSTableInfoDB dsTableInfoDB : dsTableInfoDBList){
                String tableId = dsTableInfoDB.getId().toString();
                //删除表字段
                DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
                dsFieldInfoDBExample.createCriteria().andTableIdEqualTo(tableId);
                dsFieldInfoDBMapper.deleteByExample(dsFieldInfoDBExample);
                //删除表字段关系
                DSFieldRelDBExample dsFieldRelDBExample = new DSFieldRelDBExample();
                dsFieldRelDBExample.createCriteria().andTableIdEqualTo(tableId);
                dsFieldRelDBMapper.deleteByExample(dsFieldRelDBExample);
            }
            //删除数据源表
            dsTableInfoDBMapper.deleteByExample(dsTableInfoDBExample);
        }

        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andIdEqualTo(Integer.parseInt(data_source_id));
        int _activeRows = dsMetaInfoDBMapper.deleteByExample(_ex);
        if(_activeRows > 0 )
            ret = true;
        return ret;
    }

    public boolean chkDSName(String dsName){
        boolean ret = false;
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andDsNameEqualTo(dsName);
        List<DSMetaInfoDB> _retSql = dsMetaInfoDBMapper.selectByExample(_ex,defaultSqlStart,defaultPageSize);
        if(_retSql.size() == 0 )
            ret = true;
        return ret;
    }

    public Map<String, Object> extractTables(String dsName,String keyword) throws Exception{
        Map<String,Object> _ret = new HashMap<>();
        List<Map<String,Object>> _dbs_info = new ArrayList<>();

        HiveExtractImp _extract = getExtractObj(dsName);
        String _keyword = keyword;
        if(_extract != null){
            List<String> dbs = _extract.getDatabases();
            for(String database : dbs){
                Map<String,Object> _databaseMap = new  HashMap<>();
                _databaseMap.put("id",database);
                _databaseMap.put("name",database);
                _databaseMap.put("type","db");

                if(keyword != null){
                    //如果关键字在库里出现，则列举所有的表
                    if(database.indexOf(keyword) != -1)
                        _keyword = null;
                    else
                        _keyword = keyword;
                }



                List<Map<String,String>> tbs = _extract.getTables(database,_keyword);
                List<Map<String,String>> _childs = new ArrayList<>();

                for(Map<String,String> table:tbs){
                    Map<String,String> _tableMap = new HashMap<>();
                    _tableMap.put("id",database+"."+table.get("TBL_NAME"));
                    _tableMap.put("name",table.get("TBL_NAME"));
                    _tableMap.put("type",table.get("TBL_TYPE"));
                    _childs.add(_tableMap);
                }

                //如果查不到表，则不加入返回
                if(_childs.size() != 0){
                    _databaseMap.put("children",_childs);
                    _dbs_info.add(_databaseMap);
                }

            }
        }

        _ret.put("data",_dbs_info);
        _ret.put("total",_dbs_info.size());
        return _ret;
    }

    private HiveExtractImp getExtractObj(String dsName){
        HiveExtractImp _extract = null;

        //需要确定当ds信息还没入库时，抽取表的

        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andDsNameEqualTo(dsName);
        List<DSMetaInfoDB> _sqlRet =  dsMetaInfoDBMapper.selectByExample(_ex,defaultSqlStart,defaultPageSize);
        if(_sqlRet.size() > 0 ) {
            DSMetaInfoDB _info_from_db = _sqlRet.get(0);

            DSMetaInfo _info = new DSMetaInfo();
            HiveMetaInfo _meta = new HiveMetaInfo();
            _meta.setMysqlUrl(_info_from_db.getHiveMetaMysqlUrl());
            _meta.setMysqlUser(_info_from_db.getHiveMetaUsername());
            _meta.setMysqlPass(util.DecodePassword(_info_from_db.getHiveMetaPswd()));
            _info.setHiveMetaInfo(_meta);
            _extract = new HiveExtractImp(_info);
        }
        return _extract;
    }

    @Transactional
    public boolean saveTablesAndFields(JSONObject json) throws Exception{
        boolean _ret = true;
        String dsId = json.getString("data_source_id");
        String dsName = json.getString("data_source_name");
        List<String> tables = (List) json.get("table_names");
        if(tables == null) {
            throw new Exception("miss tables name info");
        }
        if(dsName == null && dsId == null){
            throw new Exception("input data_source_name or data_source_id ");
        }
        if(dsName != null && !dsName.equals("")){
            DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
            dsMetaInfoDBExample.createCriteria().andDsNameEqualTo(dsName);
            List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);
            dsId = dsMetaInfoDBList.get(0).getId().toString();
        }else{
            DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
            dsMetaInfoDBExample.createCriteria().andIdEqualTo(Integer.parseInt(dsId));
            List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);
            dsName = dsMetaInfoDBList.get(0).getDsName();
        }
        List<Map<String,Object>> mapList = new ArrayList<>();

        HiveExtractImp hiveExtractImp = getExtractObj(dsName);
        //for(tables)
        //1.save tables in local db
        List<Map<String,String>> tableList = new ArrayList<>();
        String oldTable="";
        for(String tableName : tables){
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(tableName).andDatasourceIdEqualTo(dsId);
            List<DSTableInfoDB> dsTableInfoDBSList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            if(dsTableInfoDBSList.size() > 0) {
                oldTable += tableName;
                continue;
            }
            Map<String,String> tableMap = new HashMap<>();
            tableMap.put("table_english_name",tableName);
            tableMap.put("datasource_id",dsId);
            List<Map<String,String>> tbs = getExtractObj(dsName).getTables(tableName.split("\\.")[0],tableName.split("\\.")[1]);
            tableMap.put("table_type",tbs.get(0).get("TBL_TYPE"));
            tableList.add(tableMap);
        }

        //如果是全部都是老表 不需要处理后续逻辑
        if(tableList.size() == 0) {
            return true;
        }

        if(dsTableInfoDBMapper.insertTables(tableList) <= 0){
            _ret = false;
        }

        //2.save fields in local db
        for(Map<String,String> tableMap : tableList){
            String[] dbTableName = tableMap.get("table_english_name").split("\\.");
            List<FieldEntity> fieldList = hiveExtractImp.getFields(dbTableName[0], dbTableName[1]);
            for(FieldEntity fieldEntity : fieldList){
                Map<String, Object> fieldMap = new HashMap<>();
                fieldMap.put("datasource_id",dsId);
                DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
                dsTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(tableMap.get("table_english_name")).andDatasourceIdEqualTo(dsId);
                List<DSTableInfoDB> list = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
                fieldMap.put("table_id",list.get(0).getId());
                fieldMap.put("field_english_name",fieldEntity.getName());
                String field = fieldEntity.getType();
                fieldMap.put("field_type",field);
                if (field.contains("varchar")){
                    fieldMap.put("field_length",field.substring(field.indexOf("(")+1,field.indexOf(")")));
                }else {
                    fieldMap.put("field_length",null);
                }
                mapList.add(fieldMap);
            }
        }
        if(dsFieldInfoDBMapper.insertFields(mapList) <= 0){
            _ret = false;
        }
        Map<String,Object> insertFeildMap = dsFieldInfoDBMapper.selectAll(mapList);
        //3.add relation
        int i=0;
        String[] dbName = new String[100000];
        String[] name = new String[100000];
        for (String tableName : tables) {
            String[] dbTableName = tableName.split("\\.");
            dbName[i] = dbTableName[0];
            name[i] = dbTableName[1];
            i++;
        }
//        String[] names = new String[100000];
        ArrayList<String> nameList = new ArrayList<>();
        int j=1,k=1,isprimary=0;
        nameList.add(dbName[0]);
        for(; j < i; j++){
            if(dbName[j].equals(dbName[j-1])){
                nameList.add(name[j]);
            }else{
                nameList = new ArrayList<>();
                Set<KeyInfo> set = hiveExtractImp.getKeyInfo(dbName[j-1],(String[]) nameList.toArray(new String[nameList.size()]));
                Iterator it = set.iterator();
                while(it.hasNext()){
                    KeyInfo keyInfo = (KeyInfo) it.next();
                    String foreignFieldId;
                    if(keyInfo.getKeyType() == "PK"){
                        isprimary=1;
                    }else{
                        isprimary=0;
                    }
                    String tbname = dbName[j-1] +"."+ keyInfo.getTblName();
                    DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
                    dsTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(tbname).andDatasourceIdEqualTo(dsId);
                    List<DSTableInfoDB> list = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
                    String tbId = list.get(0).getId().toString();
                    String fieldName = keyInfo.getFieldName();
                    //获取列字段的id
                    Map<String,Integer> feildIdMap = (Map<String, Integer>) insertFeildMap.get(tbId+"."+fieldName);
                    String fieldId = feildIdMap.get("value").toString();
                    if(keyInfo.getIncidenceTBL() == null){
                        foreignFieldId = null;
                        if(!oldTable.contains(tbId)){
                            dsFieldRelDBMapper.insertReleations(tbId,fieldId,null,foreignFieldId,isprimary);
                        }
                    }else{
                        //获取外键表字段id
                        String foreignTbName = keyInfo.getIncidenceTBL();
                        DSTableInfoDBExample dsForeignTableInfoDBExample = new DSTableInfoDBExample();
                        dsForeignTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(dbName[j-1] + "." + foreignTbName).andDatasourceIdEqualTo(dsId);
                        List<DSTableInfoDB> foreignList = dsTableInfoDBMapper.selectByExample(dsForeignTableInfoDBExample);
                        String foreignId = foreignList.get(0).getId().toString();
                        Map<String,Integer> foreignFeildIdMap = (Map<String, Integer>) insertFeildMap.get(foreignId+"."+fieldName);
                        foreignFieldId = foreignFeildIdMap.get("value").toString();

                        String foreigntb = dbName[j-1] + "." + keyInfo.getIncidenceTBL();
                        DSTableInfoDBExample ds = new DSTableInfoDBExample();
                        ds.createCriteria().andTableEnglishNameEqualTo(foreigntb);
                        if(!oldTable.contains(list.get(0).getId().toString())){
                            dsFieldRelDBMapper.insertReleations(tbId,fieldId,foreignId,foreignFieldId,isprimary);
                        }
                    }

                }
            }
        }
        Set<KeyInfo> set = hiveExtractImp.getKeyInfo(dbName[j-1],(String[]) nameList.toArray(new String[nameList.size()]));
        Iterator it = set.iterator();
        String tbname = tables.get(tables.size()-1);
        while(it.hasNext()){
            KeyInfo keyInfo = (KeyInfo) it.next();
            if(keyInfo.getKeyType() == "PK"){
                isprimary=1;
            }else{
                isprimary=0;
            }
            String foreignFieldId;
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(dbName[j-1] + "." + keyInfo.getTblName()).andDatasourceIdEqualTo(dsId);
            List<DSTableInfoDB> list = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            String tbId = list.get(0).getId().toString();
            String fieldName = keyInfo.getFieldName();
            //获取列字段的id
            Map<String,Integer> feildIdMap = (Map<String, Integer>) insertFeildMap.get(tbId+"."+fieldName);
            String fieldId = feildIdMap.get("value").toString();
            if(keyInfo.getIncidenceTBL() == null){
                foreignFieldId = null;
                if(!oldTable.contains(tbId)){
                    dsFieldRelDBMapper.insertReleations(tbId,fieldId,null,foreignFieldId,isprimary);
                }
            }else{
                //获取外键表字段id
                String foreignTbName = keyInfo.getIncidenceTBL();
                DSTableInfoDBExample dsForeignTableInfoDBExample = new DSTableInfoDBExample();
                dsForeignTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(dbName[j-1] + "." + foreignTbName).andDatasourceIdEqualTo(dsId);
                List<DSTableInfoDB> foreignList = dsTableInfoDBMapper.selectByExample(dsForeignTableInfoDBExample);
                String foreignId = foreignList.get(0).getId().toString();
                Map<String,Integer> foreignFeildIdMap = (Map<String, Integer>) insertFeildMap.get(foreignId+"."+fieldName);
                foreignFieldId = foreignFeildIdMap.get("value").toString();

                String foreigntb = dbName[j-1] + "." + keyInfo.getIncidenceTBL();
                DSTableInfoDBExample ds = new DSTableInfoDBExample();
                ds.createCriteria().andTableEnglishNameEqualTo(foreigntb);
                if(!oldTable.contains(list.get(0).getId().toString())){
                    dsFieldRelDBMapper.insertReleations(tbId,fieldId,foreignId,foreignFieldId,isprimary);
                }
            }

        }

        return _ret;
    }

    public Map<String, Object> relationList(JSONObject json) throws Exception{

        Map<String,Object> responseMap = new HashMap<>();
        List<Object> tableInfo = new ArrayList<>();
        List<Object> tableRelation = new ArrayList<>();

        String dsId = json.getString("data_source_id");
        String dsName = json.getString("data_source_name");
        if(dsName != null && !dsName.equals("")){
            DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
            dsMetaInfoDBExample.createCriteria().andDsNameEqualTo(dsName);
            List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);
            dsId = dsMetaInfoDBList.get(0).getId().toString();
        }
        List<String> tbList = (List<String>) json.get("table_list");

        if(tbList == null){
            tbList = new ArrayList<>();
            //获取dsName对应得所有表
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsId);
            List<DSTableInfoDB> tableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            for(DSTableInfoDB dsTableInfoDB : tableInfoDBList){
                tbList.add(dsTableInfoDB.getTableEnglishName());
            }
        }
        for(int i=0; i < tbList.size(); i++){
            Map<String, Object> fieldMap = new HashMap<>();
            //获取表id
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsId).andTableEnglishNameEqualTo(tbList.get(i));
            List<DSTableInfoDB> tableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            fieldMap.put("id",tableInfoDBList.get(0).getId());
            fieldMap.put("name",tbList.get(i));

            //获取表列
            DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
            dsFieldInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsId).andTableIdEqualTo(tableInfoDBList.get(0).getId().toString());
            List<DSFieldInfoDB> fieldList = dsFieldInfoDBMapper.selectByExample(dsFieldInfoDBExample);

            List<Map<String,String>> resultList = new ArrayList<>();
            for(DSFieldInfoDB list : fieldList){
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("field",list.getFieldEnglishName());
                resultMap.put("name",list.getFieldEnglishName());
                resultList.add(resultMap);
            }
            fieldMap.put("fields",resultList);
            tableInfo.add(fieldMap);

            //获取表列关联关系
            for(int j=0; j<tbList.size(); j++){
                DSTableInfoDBExample ds = new DSTableInfoDBExample();
                ds.createCriteria().andDatasourceIdEqualTo(dsId).andTableEnglishNameEqualTo(tbList.get(j));
                List<DSTableInfoDB> foreignTableInfoDBList = dsTableInfoDBMapper.selectByExample(ds);

                DSFieldRelDBExample dsFieldRelDBExample = new DSFieldRelDBExample();
                dsFieldRelDBExample.createCriteria().andTableIdEqualTo(tableInfoDBList.get(0).getId().toString()).andForeignTableIdEqualTo(foreignTableInfoDBList.get(0).getId().toString());
                List<DSFieldRelDB> relationList = dsFieldRelDBMapper.selectByExample(dsFieldRelDBExample);
                for(DSFieldRelDB dsFieldRelDB : relationList){
                    Map<String,String> relationResultMap = new HashMap<>();
                    relationResultMap.put("from", dsFieldRelDB.getTableId());
                    relationResultMap.put("to", dsFieldRelDB.getForeignTableId());
                    relationResultMap.put("relation_id", dsFieldRelDB.getRelationId().toString());
                    relationResultMap.put("relation", dsFieldRelDB.getRelation());
                    DSFieldInfoDB dsFieldInfoDB = dsFieldInfoDBMapper.selectByPrimaryKey(Integer.parseInt(dsFieldRelDB.getFieldId()));
                    relationResultMap.put("from_field", dsFieldInfoDB.getFieldEnglishName());
                    DSFieldInfoDB ForeignDsFieldInfoDB = dsFieldInfoDBMapper.selectByPrimaryKey(Integer.parseInt(dsFieldRelDB.getForeignFieldId()));
                    relationResultMap.put("to_field", ForeignDsFieldInfoDB.getFieldEnglishName());
                    tableRelation.add(relationResultMap);
                }
            }
        }
        responseMap.put("table_info", tableInfo);
        responseMap.put("table_relation",tableRelation);
        return responseMap;
    }

    public int relationSave(JSONObject jsonParam){
        Map<String, Object> map = new HashMap<>();
        DSFieldRelDB dsFieldRelDB = new DSFieldRelDB();
        String from = jsonParam.getString("from");
        String from_field = jsonParam.getString("from_field");
        dsFieldRelDB.setTableId(from);

        DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
        dsFieldInfoDBExample.createCriteria().andTableIdEqualTo(from).andFieldEnglishNameEqualTo(from_field);
        List<DSFieldInfoDB> dsFieldInfoDBList = dsFieldInfoDBMapper.selectByExample(dsFieldInfoDBExample);
        dsFieldRelDB.setFieldId(dsFieldInfoDBList.get(0).getId().toString());

        String to = jsonParam.getString("to");
        String to_field = jsonParam.getString("to_field");
        dsFieldRelDB.setForeignTableId(to);

        DSFieldInfoDBExample dsforeign = new DSFieldInfoDBExample();
        dsforeign.createCriteria().andTableIdEqualTo(to).andFieldEnglishNameEqualTo(to_field);
        List<DSFieldInfoDB> dsforeignList = dsFieldInfoDBMapper.selectByExample(dsFieldInfoDBExample);
        dsFieldRelDB.setForeignFieldId(dsforeignList.get(0).getId().toString());
        dsFieldRelDB.setRelation(jsonParam.getString("relation"));
        if(jsonParam.getString("relation_id") == null || jsonParam.getString("relation_id").equals("")){
            return dsFieldRelDBMapper.insert(dsFieldRelDB);
        }else{
            dsFieldRelDB.setRelationId(Integer.parseInt(jsonParam.getString("relation_id")));
            return dsFieldRelDBMapper.updateByPrimaryKey(dsFieldRelDB);
        }

    }

    public int relationDel(String relation_id){
        return dsFieldRelDBMapper.deleteByPrimaryKey(Integer.parseInt(relation_id));
    }

    public Map<String,Object> createTerm(String data_source_id) throws Exception{
        Map<String,Object> responseMap = new HashMap<>();
        List<TermGenInfo> termGenInfoList = new ArrayList<>();
        List<TermLogicCatagory> termLogicCatagoryTabelList = new ArrayList<>();
        List<TermLogicCatagory> termLogicCatagoryEntityList = new ArrayList<>();

        //根据data_source_name获取所有表
        DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
        dsMetaInfoDBExample.createCriteria().andIdEqualTo(Integer.parseInt(data_source_id));
        List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);

        DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
        dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsMetaInfoDBList.get(0).getDsName());
        List<DSTableInfoDB> tableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
        //遍历所有表
        for(DSTableInfoDB dsTableInfoDB : tableInfoDBList){
            TermGenInfo termGenInfo = new TermGenInfo();
            PhysicalTableInfo physicalTableInfo = new PhysicalTableInfo();
            physicalTableInfo.setPrimaryKey(dsTableInfoDB.getPrimaryKey());
            String db = dsTableInfoDB.getTableEnglishName().split("\\.")[0];
            String tableName = dsTableInfoDB.getTableEnglishName().split("\\.")[1];
            physicalTableInfo.setTableType(dsTableInfoDB.getTableType());
            physicalTableInfo.setPhysicalDb(db);
            physicalTableInfo.setPhysicalTable(tableName);
            physicalTableInfo.setPrimaryKey(dsTableInfoDB.getPrimaryKey());
            termGenInfo.setTableInfo(physicalTableInfo);

            //获取表的所有列
            DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
            dsFieldInfoDBExample.createCriteria().andTableIdEqualTo(dsTableInfoDB.getId().toString());
            List<DSFieldInfoDB> dsFieldInfoDBList = dsFieldInfoDBMapper.selectByExample(dsFieldInfoDBExample);
            List<PhysicalFieldInfo> physicalFieldInfoList = new ArrayList<>();
            //遍历表的所有列
            if(dsFieldInfoDBList.size() == 0){
                continue;
            }
            for(DSFieldInfoDB dsFieldInfoDB : dsFieldInfoDBList){
                PhysicalFieldInfo physicalFieldInfo = new PhysicalFieldInfo();
                physicalFieldInfo.setPhysicalField(dsFieldInfoDB.getFieldEnglishName());
                physicalFieldInfo.setPhysicalDb(db);
                physicalFieldInfo.setPhysicalTable(tableName);
                physicalFieldInfo.setDataType(dsFieldInfoDB.getFieldType());
                if(dsFieldInfoDB.getFieldLength() == null){
                    physicalFieldInfo.setDataLength(null);
                }else{
                    physicalFieldInfo.setDataLength(Integer.parseInt(dsFieldInfoDB.getFieldLength()));
                }
                physicalFieldInfoList.add(physicalFieldInfo);
            }
            termGenInfo.setFieldInfoList(physicalFieldInfoList);
            termGenInfoList.add(termGenInfo);


        }
        //新建http请求
        //调用term接口插入
        String url = AppProperties.get("term.url");
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringEntity entity = new StringEntity(JSONObject.toJSONString(termGenInfoList), "utf-8");
        entity.setContentType("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = httpClient.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() != 200) {
            responseMap.put("code",500);
            responseMap.put("msg","失败");
            return responseMap;
        }

        //封装termMainInfo,获取表
        DSEntityInfoDBExample dsEntityInfoDBExample = new DSEntityInfoDBExample();
        dsEntityInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsMetaInfoDBList.get(0).getDsName());
        List<DSEntityInfoDB> dsEntityInfoDBList = dsEntityInfoDBMapper.selectByExample(dsEntityInfoDBExample);
        for(DSEntityInfoDB dsEntityInfoDB : dsEntityInfoDBList){
            TermLogicCatagory termLogicCatagory = new TermLogicCatagory();
            termLogicCatagory.setCreateTime(new Date());
            //termLogicCatagory.setPhysicalTable(dsEntityInfoDB.getMainTable().split("\\.")[1]);
            termLogicCatagory.setCategoryName(dsEntityInfoDB.getEntityName());
            termLogicCatagory.setParentId(1);
            termLogicCatagory.setDataSourceName(dsEntityInfoDB.getDatasourceId());
            termLogicCatagory.setDataSourceId(dsEntityInfoDB.getId().toString());
            termLogicCatagoryEntityList.add(termLogicCatagory);
        }
        //Entity表调用insertTermLogic接口
        String logicEntityUrl = AppProperties.get("termLogic.url");
        HttpPost httpEntityPost = new HttpPost(logicEntityUrl);
        CloseableHttpClient httpEntityClient = HttpClients.createDefault();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("termGenInfoList",termGenInfoList);
        jsonObject.put("termLogicCatagoryEntityList",termLogicCatagoryEntityList);
        StringEntity termLogicEntity = new StringEntity(jsonObject.toString(),"utf-8");
        termLogicEntity.setContentType("UTF-8");
        termLogicEntity.setContentType("application/json");
        httpEntityPost.setEntity(termLogicEntity);
        HttpResponse resp1 = httpEntityClient.execute(httpEntityPost);
        if(resp1.getStatusLine().getStatusCode() != 200) {
            responseMap.put("code",500);
            responseMap.put("msg","失败");
            return responseMap;
        }

        HttpEntity he = resp.getEntity();
        String respContent = EntityUtils.toString(he,"UTF-8");
        JSONObject resultJson = (JSONObject) JSONObject.parse(respContent);
        responseMap.put("code",resultJson.getString("code"));
        responseMap.put("msg",resultJson.getString("msg"));
        responseMap.put("term_nums",resultJson.getString("data"));



        /*HttpEntity he = resp.getEntity();
        String respContent = EntityUtils.toString(he,"UTF-8");
        JSONObject resultJson = (JSONObject) JSONObject.parse(respContent);
        responseMap.put("code",resultJson.getString("code"));
        responseMap.put("msg",resultJson.getString("msg"));
        responseMap.put("term_nums",resultJson.getString("data"));*/

        return responseMap;
    }
}

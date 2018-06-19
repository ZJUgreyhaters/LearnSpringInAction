package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.metadatamgr.data.DSMetaInfo;
import com.quantchi.metadatamgr.data.FieldEntity;
import com.quantchi.metadatamgr.data.HiveMetaInfo;
import com.quantchi.metadatamgr.data.KeyInfo;
import com.quantchi.metadatamgr.data.entity.*;
import com.quantchi.metadatamgr.data.mapper.DSFieldInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSFieldRelDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSMetaInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import com.quantchi.metadatamgr.extract.HiveExtractImp;
import javafx.scene.effect.SepiaTone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String, Object> getDSMetaInfo(String dsName, int start, int pagesize){
        Map<String,Object> _ret = new HashMap<>();
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        if(!"".equals(dsName))
            _ex.createCriteria().andDsNameEqualTo(dsName);

        List<DSMetaInfoDB> _sqlRet =  dsMetaInfoDBMapper.selectByExample(_ex,(start-1)* pagesize,pagesize);
        _ret.put("data",_sqlRet);
        _ret.put("total",_sqlRet.size());
        return _ret;
    }

    public boolean connectTest(String host,
                                 String port,
                                 String username,
                                 String password){

        return  HiveExtractImp.connectionTest(host,port,username,password);
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

    public boolean delMetaInfo(String dsName){
        boolean ret = false;
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andDsNameEqualTo(dsName);
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

    public Map<String, Object> extractTables(String dsName){
        Map<String,Object> _ret = new HashMap<>();
        List<Map<String,Object>> _dbs_info = new ArrayList<>();

        HiveExtractImp _extract = getExtractObj(dsName);
        if(_extract != null){
            List<String> dbs = _extract.getDatabases();
            for(String database : dbs){
                Map<String,Object> _databaseMap = new  HashMap<>();
                _databaseMap.put("id",database);
                _databaseMap.put("name",database);
                _databaseMap.put("type","db");

                List<String> tbs = _extract.getTables(database);
                List<Map<String,String>> _childs = new ArrayList<>();

                for(String table:tbs){
                    Map<String,String> _tableMap = new HashMap<>();
                    _tableMap.put("id",database+"."+table);
                    _tableMap.put("name",table);
                    _tableMap.put("type","table");
                    _childs.add(_tableMap);
                }
                _databaseMap.put("children",_childs);
                _dbs_info.add(_databaseMap);
            }
        }

        _ret.put("data",_dbs_info);
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
            _meta.setMysqlPass(_info_from_db.getHiveMetaPswd());
            _info.setHiveMetaInfo(_meta);
            _extract = new HiveExtractImp(_info);
        }
        return _extract;
    }

    public boolean saveTablesAndFields(String dsName,List<String> tables){
        boolean _ret = true;
        List<Map<String,String>> mapList = new ArrayList<>();

        HiveExtractImp hiveExtractImp = getExtractObj(dsName);
        //for(tables)
        //1.save tables in local db
        for(String tableName : tables){
            String[] dbTableName = tableName.split("\\.");
            List<FieldEntity> fieldList = hiveExtractImp.getFields(dbTableName[0], dbTableName[1]);
            for(FieldEntity fieldEntity : fieldList){
                Map<String, String> fieldMap = new HashMap<>();
                fieldMap.put("datasource_id",dsName);
                fieldMap.put("table_id",tableName);
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

        //2.save fields in local db
        List<Map<String,String>> tableList = new ArrayList<>();
        for(String tableName : tables){
            Map<String,String> tableMap = new HashMap<>();
            tableMap.put("table_english_name",tableName);
            tableMap.put("datasource_id",dsName);
            tableList.add(tableMap);
        }
        if(dsTableInfoDBMapper.insertTables(tableList) <= 0){
            _ret = false;
        }

        //3.add relation
        int i=0;
        String[] dbName = new String[100];
        String[] name = new String[100];
        for (String tableName : tables) {
            String[] dbTableName = tableName.split("\\.");
            dbName[i] = dbTableName[0];
            name[i] = dbTableName[1];
            i++;
        }
        String[] names = new String[100];
        names[0] = name[0];
        int j=1,k=1;
        for(; j < i; j++){
            if(dbName[j].equals(dbName[j-1])){
                names[k]=name[j];
                k++;
            }else{
                names = null;
                k=0;
                Set<KeyInfo> set = hiveExtractImp.getKeyInfo(dbName[j],names);
                Iterator it = set.iterator();
                while(it.hasNext()){
                    KeyInfo keyInfo = (KeyInfo) it.next();
                    String foreignFieldId;
                    if(keyInfo.getIncidenceTBL() == null){
                        foreignFieldId = null;
                    }else{
                        foreignFieldId = keyInfo.getFieldName();
                        dsFieldRelDBMapper.insertReleations(keyInfo.getTblName(),keyInfo.getFieldName(),keyInfo.getIncidenceTBL(),foreignFieldId);
                    }
                }
            }
        }
        Set<KeyInfo> set = hiveExtractImp.getKeyInfo(dbName[j-1],names);
        Iterator it = set.iterator();
        while(it.hasNext()){
            KeyInfo keyInfo = (KeyInfo) it.next();
            String foreignFieldId;
            if(keyInfo.getIncidenceTBL() == null){
                foreignFieldId = null;
            }else{
                foreignFieldId = keyInfo.getFieldName();
                dsFieldRelDBMapper.insertReleations(keyInfo.getTblName(),keyInfo.getFieldName(),keyInfo.getIncidenceTBL(),foreignFieldId);
            }

        }

        return _ret;
    }

    public Map<String, Object> relationList(String dsName, List<String> tbList){

        Map<String,Object> responseMap = new HashMap<>();
        List<Object> tableInfo = new ArrayList<>();
        List<Object> tableRelation = new ArrayList<>();
        if(tbList == null){
            tbList = new ArrayList<>();
            //获取dsName对应得所有表
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsName);
            List<DSTableInfoDB> tableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            for(DSTableInfoDB dsTableInfoDB : tableInfoDBList){
                tbList.add(dsTableInfoDB.getTableEnglishName());
            }
        }
        for(int i=0; i < tbList.size(); i++){
            Map<String, Object> fieldMap = new HashMap<>();
            //获取表id
            DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
            dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsName).andTableEnglishNameEqualTo(tbList.get(i));
            List<DSTableInfoDB> tableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);
            fieldMap.put("id",tableInfoDBList.get(0).getId());
            fieldMap.put("name",tbList.get(i));

            //获取表列
            DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
            dsFieldInfoDBExample.createCriteria().andDatasourceIdEqualTo(dsName).andTableIdEqualTo(tbList.get(i));
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
            for(int j=i+1; j<tbList.size(); j++){
                DSFieldRelDBExample dsFieldRelDBExample = new DSFieldRelDBExample();
                String fieldId = tbList.get(i).split("\\.")[1];
                String foreignId = tbList.get(j).split("\\.")[1];
                dsFieldRelDBExample.createCriteria().andTableIdEqualTo(fieldId).andForeignTableIdEqualTo(foreignId);
                List<DSFieldRelDB> relationList = dsFieldRelDBMapper.selectByExample(dsFieldRelDBExample);
                for(DSFieldRelDB dsFieldRelDB : relationList){
                    Map<String,String> relationResultMap = new HashMap<>();
                    relationResultMap.put("from", dsFieldRelDB.getTableId());
                    relationResultMap.put("to", dsFieldRelDB.getForeignTableId());
                    relationResultMap.put("relation_id", dsFieldRelDB.getRelationId().toString());
                    relationResultMap.put("relation", dsFieldRelDB.getRelation());
                    relationResultMap.put("from_field", dsFieldRelDB.getFieldId());
                    relationResultMap.put("to_field", dsFieldRelDB.getForeignFieldId());
                    tableRelation.add(relationResultMap);
                }
            }
        }
        responseMap.put("table_info", tableInfo);
        responseMap.put("table_relation",tableRelation);
        return responseMap;
    }

    public Map<String, Object> relationSave(JSONObject jsonParam){
        DSFieldRelDBExample dsFieldRelDBExample = new DSFieldRelDBExample();
        dsFieldRelDBExample.createCriteria().andTableIdEqualTo(jsonParam.getString("from"))
                .andForeignTableIdEqualTo(jsonParam.getString("to"))
                .andFieldIdEqualTo(jsonParam.getString("from_field"))
                .andForeignFieldIdEqualTo(jsonParam.getString("to_field"));
        return null;
    }
}

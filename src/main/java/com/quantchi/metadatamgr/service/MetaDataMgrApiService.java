package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.metadatamgr.data.DSMetaInfo;
import com.quantchi.metadatamgr.data.HiveMetaInfo;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDB;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSMetaInfoDBMapper;
import com.quantchi.metadatamgr.extract.HiveExtractImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaDataMgrApiService {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrApiService.class);

    private static final String HIVETYPE = "Hive";
    private static final String HIVEMYSQLURL = "data_source_mysql_url";
    private static final String HIVEMYSQLUSER = "data_source_mysql_usr";
    private static final String HIVEMYSQLPSWD = "data_source_mysql_pswd";

    private static final int defaultSqlStart = 0;
    private static final int defaultPageSize = 10;

    @Autowired
    private  DSMetaInfoDBMapper dsMetaInfoDBMapper;

    public Map<String, Object> getDSMetaInfo(String dsName, int start, int pagesize){
        Map<String,Object> _ret = new HashMap<>();
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        if("".equals(dsName))
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
            int _activeRows = dsMetaInfoDBMapper.updateByExample(_record,_ex);
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
        if(_retSql.size() > 0 )
            ret = true;
        return ret;
    }

    public Map<String, Object> extractTables(String dsName){
        Map<String,Object> _ret = new HashMap<>();
        List<Map<String,Object>> _dbs_info = new ArrayList<>();
        DSMetaInfoDBExample _ex = new DSMetaInfoDBExample();
        _ex.createCriteria().andDsNameEqualTo(dsName);
        List<DSMetaInfoDB> _sqlRet =  dsMetaInfoDBMapper.selectByExample(_ex,defaultSqlStart,defaultPageSize);
        if(_sqlRet.size() > 0 ){
            DSMetaInfoDB _info_from_db = _sqlRet.get(0);

            DSMetaInfo _info = new DSMetaInfo();
            HiveMetaInfo _meta = new HiveMetaInfo();
            _meta.setMysqlUrl(_info_from_db.getHiveMetaMysqlUrl());
            _meta.setMysqlUser(_info_from_db.getHiveMetaUsername());
            _meta.setMysqlPass(_info_from_db.getHiveMetaPswd());
            _info.setHiveMetaInfo(_meta);
            HiveExtractImp _extract = new HiveExtractImp(_info);
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
}

package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDB;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDBExample;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSFieldInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaDataMgrFieldApiService {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrFieldApiService.class);

    @Autowired
    private DSFieldInfoDBMapper dsFieldInfoDBMapper;

    @Autowired
    private DSTableInfoDBMapper dsTableInfoDBMapper;

    public Map<String, Object> search(JSONObject jsonParam){
        DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
        dsTableInfoDBExample.createCriteria().andTableEnglishNameEqualTo(jsonParam.getString("table_name")).andDatasourceIdEqualTo(jsonParam.getString("data_source_name"));
        List<DSTableInfoDB> tableList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);

        DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
        dsFieldInfoDBExample.createCriteria().andDatasourceIdEqualTo(jsonParam.getString("data_source_name"))
                .andTableIdEqualTo(tableList.get(0).getId().toString())
                .andFieldEnglishNameEqualTo(jsonParam.getString("keywords"));
        List<DSFieldInfoDB> list = dsFieldInfoDBMapper.selectByExample(dsFieldInfoDBExample);
        PageInfo<DSFieldInfoDB> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(DSFieldInfoDB dsFieldInfoDB : list){
            Map<String,Object> fieldMap = new HashMap<>();
            fieldMap.put("field_english_name",dsFieldInfoDB.getFieldEnglishName());
            fieldMap.put("field_chinese_name",dsFieldInfoDB.getFieldChineseName());

            DSTableInfoDBExample ds = new DSTableInfoDBExample();
            ds.createCriteria().andIdEqualTo(Integer.parseInt(dsFieldInfoDB.getTableId()));
            List<DSTableInfoDB> dslist = dsTableInfoDBMapper.selectByExample(ds);

            fieldMap.put("table",dslist.get(0).getTableEnglishName());
            fieldMap.put("datasource",dsFieldInfoDB.getDatasourceId());
            fieldMap.put("type",dsFieldInfoDB.getFieldType());
            fieldMap.put("length",dsFieldInfoDB.getFieldLength());
            fieldMap.put("isterm",dsFieldInfoDB.getIsterm());
            mapList.add(fieldMap);
        }
        map.put("data", mapList);
        return map;
    }

}

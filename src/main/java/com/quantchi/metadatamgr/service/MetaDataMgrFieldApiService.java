package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDB;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDBExample;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSFieldInfoDBMapper;
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

    public Map<String, Object> search(JSONObject jsonParam){
        DSFieldInfoDBExample dsFieldInfoDBExample = new DSFieldInfoDBExample();
        dsFieldInfoDBExample.createCriteria().andDatasourceIdEqualTo(jsonParam.getString("data_source_name"))
                .andTableIdEqualTo(jsonParam.getString("table_name"))
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
            fieldMap.put("table",dsFieldInfoDB.getTableId());
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

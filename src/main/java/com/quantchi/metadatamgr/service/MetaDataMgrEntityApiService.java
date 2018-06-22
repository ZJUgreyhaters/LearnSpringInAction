package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDB;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSEntityInfoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaDataMgrEntityApiService {

    @Autowired
    private DSEntityInfoDBMapper dsEntityInfoDBMapper;

    public int createEntity(JSONObject json){
        DSEntityInfoDB dsEntityInfoDB = new DSEntityInfoDB();
        dsEntityInfoDB.setEntityName(json.getString("entity_name"));
        dsEntityInfoDB.setBusiness(json.getString("business"));
        dsEntityInfoDB.setDatasourceId(json.getString("data_source_name"));
        dsEntityInfoDB.setMainTable(json.getString("main_table_id"));
        dsEntityInfoDB.setEntityField(json.getString("main_entity_field_id"));
        String nonMainTable = String.join(",", (List)json.get("non_main_table_id"));
        dsEntityInfoDB.setNonMainTable(nonMainTable);

        return dsEntityInfoDBMapper.insert(dsEntityInfoDB);
    }

    public int modifyEntity(JSONObject json){
        DSEntityInfoDB dsEntityInfoDB = new DSEntityInfoDB();
        dsEntityInfoDB.setId(Integer.parseInt(json.getString("entity_id")));
        dsEntityInfoDB.setEntityName(json.getString("entity_name"));
        dsEntityInfoDB.setBusiness(json.getString("business"));
        dsEntityInfoDB.setDatasourceId(json.getString("data_source_name"));
        dsEntityInfoDB.setMainTable(json.getString("main_table_id"));
        dsEntityInfoDB.setEntityField(json.getString("main_entity_field_id"));
        String nonMainTable = String.join(",", (List)json.get("non_main_table_id"));
        dsEntityInfoDB.setNonMainTable(nonMainTable);

        return dsEntityInfoDBMapper.updateByPrimaryKeySelective(dsEntityInfoDB);
    }

    public Map<String, Object> searchEntity(JSONObject json){
        Map<String,Object> responseMap = new HashMap<>();
        DSEntityInfoDBExample dsEntityInfoDBExample = new DSEntityInfoDBExample();
        DSEntityInfoDBExample.Criteria _cr = dsEntityInfoDBExample.createCriteria();
        if(json.getString("business") != null && !json.getString("business").equals("")){
            _cr.andBusinessEqualTo(json.getString("business"));
        }
        if(json.getString("keywords") != null && !json.getString("keywords").equals("")){
            _cr.andEntityNameLike("%"+json.getString("keywords")+"%");
        }
        int page = Integer.parseInt(json.getString("page"));
        int page_size = Integer.parseInt(json.getString("page_size"));
        PageHelper.startPage(page, page_size);
        List<DSEntityInfoDB> list = dsEntityInfoDBMapper.selectByExample(dsEntityInfoDBExample);

        PageInfo<DSEntityInfoDB> pageInfo = new PageInfo<>(list);
        List<Map<String,Object>> responseList = new ArrayList<>();
        List<DSEntityInfoDB> dsList = pageInfo.getList();
        for(DSEntityInfoDB dsEntityInfoDB : dsList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",dsEntityInfoDB.getId());
            map.put("entity_name",dsEntityInfoDB.getEntityName());
            map.put("business",dsEntityInfoDB.getBusiness());
            map.put("data_source_name",dsEntityInfoDB.getDatasourceId());
            map.put("main_table_id",dsEntityInfoDB.getMainTable());
            map.put("main_entity_field_id",dsEntityInfoDB.getEntityField());
            map.put("non_main_table_id",dsEntityInfoDB.getNonMainTable());
            responseList.add(map);
        }
        responseMap.put("data",responseList);
        responseMap.put("total",pageInfo.getTotal());
        return responseMap;
    }

    public int deleteEntity(String id){
        return dsEntityInfoDBMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }
}

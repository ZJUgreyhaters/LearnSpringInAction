package com.quantchi.metadatamgr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDB;
import com.quantchi.metadatamgr.data.mapper.DSFieldInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataMgrFieldApiService {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrFieldApiService.class);

    @Autowired
    private DSFieldInfoDBMapper dsFieldInfoDBMapper;

    @Autowired
    private DSTableInfoDBMapper dsTableInfoDBMapper;

    public Map<String, Object> search(Map<String, Object> map){

        int page = Integer.parseInt(map.get("page").toString());
        int page_size = Integer.parseInt(map.get("page_size").toString());

        PageHelper.startPage(page, page_size);
        List<DSFieldInfoDB> list = dsFieldInfoDBMapper.selectFieldInfoBytableId(map);
        PageInfo<DSFieldInfoDB> pageInfo = new PageInfo<>(list);
        map.put("total",pageInfo.getTotal());
        map.put("data", list);
        return map;
    }

}

package com.quantchi.metadatamgr.data.mapper;


import com.quantchi.metadatamgr.data.entity.MDMountNodeInfo;
import com.quantchi.metadatamgr.data.entity.MdRuletypeNodeInfo;

import java.util.List;
import java.util.Map;


public interface MDMountNodeInfoMapper {

    List<MDMountNodeInfo> getMountNodeInfo();
    List<MDMountNodeInfo> searchMountNodeInfo(String keyword);
    List<MDMountNodeInfo> searchMountNodeInfoByParentId(List<String> parentIds);
    List<MDMountNodeInfo> getTableMountNodeInfo(String datasourceId);
    List<MDMountNodeInfo> getFieldsMountNodeInfo(String tableId);
    List<Map<String,Object>> getBusinSystemList(Map<String ,Object> map);
    List<MdRuletypeNodeInfo> getRuleTypeNodeInfo();

}
package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

public interface TableDetailMapper {
    void insertTableDetail(Map<String, Object> map);

    List<Map<String, Object>> selectTableDetail();

    void insertTableDetailRela(Map<String, Object> map);

    void deleAuthTabRela(Integer authId);

    void deleTabByAuthid(Integer authId);

    void deleAuthTabRelaByTabId(Integer tabId);

    void deleTabByTabId(Integer tabId);

    void modifyTableDetail(Map<String, Object> map);


}

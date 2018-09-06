package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

public interface TabColumnDetailMapper {
    void insertTabColDetail(Map<String, Object> map);

    List<Map<String, Object>> selectTabColDetail();

    void insertColDetailRela(Map<String, Object> map);

    void deleAuthColRela(Integer authId);

    void deleAuthColRelaByColId(Integer colId);

    void deleColByColId(Integer colId);

    void modifyColuDetail(Map<String, Object> map);

    List<Map<String, Object>> getTabColDetailRelaByAuthId(Integer authId);

    List<Map<String, Object>> getTabColDetailByAuthId(Integer authId);

}

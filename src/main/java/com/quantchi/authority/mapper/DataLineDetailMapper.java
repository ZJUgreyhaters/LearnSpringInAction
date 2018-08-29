package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

public interface DataLineDetailMapper {
    void insertLineDetail(Map<String, Object> map);

    List<Map<String, Object>> selectLineDetail();

    void insertLineDetailRela(Map<String, Object> map);

    void deleAuthLineRela(Integer authId);

    void deleLineByAuthid(Integer authId);

    void deleAuthLineRelaByLineId(Integer lineId);

    void deleLineByLineId(Integer lineId);

    void modifyLineDetail(Map<String, Object> map);
}

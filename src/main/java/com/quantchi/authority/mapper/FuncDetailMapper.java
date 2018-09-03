package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

public interface FuncDetailMapper {

    void insertFuncDetail(Map<String, Object> map);

    List<Map<String, Object>> selectFuncDetail();

    void insertFuncDetailRela(Map<String, Object> map);

    void deleAuthFuncRela(Integer authId);

    void deleAuthFuncRelaByFuncId(Integer funcId);

    void deleFuncByFuncId(Integer funcId);

    void modifyFuncDetail(Map<String, Object> map);

    List<Map<String, Object>> getFuncDetailRelaByAuthId(Integer authId);

    List<Map<String, Object>> getFuncDetailByAuth(Integer authId);



}

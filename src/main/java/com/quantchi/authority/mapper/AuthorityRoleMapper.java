package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

public interface AuthorityRoleMapper {

    List<Map<String, Object>> getAuthRole();

    List<Map<String, Object>> getAuthRoleAndUserCount();

    void insertRole(Map<String, Object> map);

    List<Map<String, Object>>  getRoleAuthDetail(Integer roleId);

    List<Map<String, Object>>  getRoleAuthDetailByName(String name);

    void deleRole(Integer roleId);

    void deleUserRole(Integer roleId);

    void updateRole(Map<String, Object> map);

    List<Map<String, Object>>  getRoleByFilter(Map<String, Object> map);
}

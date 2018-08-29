package com.quantchi.authority.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/8/23.
 */
public interface AuthorityMapper {
    List<Map<String, Object>> selectAuth();

    void insertAuth(Map<String, Object> map);

    void inertAuthRoleRela(Map<String, Object> map);

    void deleRoleRelaByRoleId(Integer roleId);

    void deleRoleRelaByRelaId(Integer relaId);

    void deleRoleRelaByAuthId(Integer authId);

    void deleAuth(Integer authId);

    void updateAuth(Map<String, Object> map);
}

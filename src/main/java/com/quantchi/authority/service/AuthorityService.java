package com.quantchi.authority.service;

import java.util.Map;

/**
 * Created by 49537 on 2018/8/23.
 */
public interface AuthorityService {
   /**modify date:20180823
    * author :cjl
    * 查询所有角色
    */
    String selectRoleList(Map<String, Object> map);
    /**modify date:20180823
    * author :cjl
    * 查询所有角色和用户数
    */
   String selectRoleListAndUserCount();
    /**modify date:20180823
     * author :cjl
     * 增加角色
     */
    void addRole(Map<String, Object> map);

    /**modify date:20180823
     * author :cjl
     * 增加权限
     */
    void addAuth(Map<String, Object> map);
    /**modify date:20180823
    * author :cjl
    * 增加新角色和角色权限关系
    */
    void addRoleAuth(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 查询所有权限
     */
    String selectAuthList(Map<String, Object> map);

    /**modify date:20180823
     * author :cjl
     * 增加角色和权限关系
     */
    void  addAuthRoleRela(Map<String, Object> map);
    /**modify date:20180823
    * author :cjl
    * 查询指定的角色的所拥有的权限
    */
    String getRoleAuthDetail(Integer roleId) ;
    /**modify date:20180823
    * author :cjl
    * 通过名字查询角色的所拥有的权限
    */
    String getRoleAuthDetailByName(String name);
    /**modify date:20180823
    * author :cjl
    * 删除角色
    */
    void deleRole(String roleId);

    /**modify date:20180823
    * author :cjl
    * 删除角色与权限关系
    */
    void deleRoleAuthRela(Map<String, Object> map);
    /**modify date:20180823
    * author :cjl
    * 修改角色描述
    */
   void modifyRole(Map<String, Object> map);
   /**modify date:20180823
   * author :cjl
   * 修改权限描述
   */
   void modifyAuth(Map<String, Object> map);
   /**modify date:20180823
  * author :cjl
  * 多条件查询权限
  */
   String getAuthByFilter(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 多条件查询角色
     */
    String getRoleByFilter(Map<String, Object> map);

}

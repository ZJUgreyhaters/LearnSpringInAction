package com.quantchi.authority.service;

import java.util.Map;

public interface AuthorityDetailService {
    /**modify date:20180823
     * author :cjl
     * 查询所有表权限明细
     */
    String selectTableDetailList();
    /**modify date:20180823
     * author :cjl
     * 增加表权限明细
     */
    void addTableDetail(Map<String, Object> map);

    /**modify date:20180823
     * author :cjl
     * 增加字段权限明细
     */
    void addTableColDetail(Map<String, Object> map);

    /**modify date:20180823
     * author :cjl
     * 查询所有字段权限明细
     */
    String selectTableColDetailList();
    /**modify date:20180823
     * author :cjl
     * 增加行数据权限明细
     */
    void addDataLineDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 查询所有行数据权限明细
     */
    String selectDataLineDetail();
    /**modify date:20180823
     * author :cjl
     * 增加功能权限明细
     */
    void addFuncDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 查询功能权限明细
     */
    String selectFuncDetail();
    /**modify date:20180823
     * author :cjl
     * 增加权限与数据权限明细
     */
    void addAuthAndDataDetail(Map<String, Object> map);

    /**modify date:20180823
     * author :cjl
     * 增加权限与数据权限明细
     */
    void addAuthAndFuncDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 删除权限
     */
    void deleAuthByAuth(Integer authId);
    /**modify date:20180823
     * author :cjl
     * 删除行权限明细
     */
    void deleLineDetail(String lineId);
    /**modify date:20180823
     * author :cjl
     * 删除字段权限明细
     */
    void deleColuDetail(String coluId);
    /**modify date:20180823
     * author :cjl
     * 删除表权限明细
     */
    void deleTableDetail(String tableId);
    /**modify date:20180823
     * author :cjl
     * 删除功能权限明细
     */
    void deleFuncDetail(String funcId);

    /**modify date:20180823
     * author :cjl
     * 修改行数据权限明细
     */
    void modifyLineDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 修改字段权限明细
     */
    void modifyColuDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 修改表权限明细
     */
    void modifyTableDetail(Map<String, Object> map);
    /**modify date:20180823
     * author :cjl
     * 修改功能权限明细
     */
    void modifyFuncDetail(Map<String, Object> map);

   /*查询权限的明细*/
    String getAuthdetail(Map<String, Object> map);

    /*查询功能权限的明细*/
    String getFuncDetailList();

}

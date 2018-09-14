package com.quantchi.authority.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import com.quantchi.authority.shiro.RoleListContext;
import com.quantchi.authority.shiro.SysPermissionInitService;
import com.quantchi.authority.sqlparser.ColumnPermission;
import com.quantchi.authority.sqlparser.RowPermission;
import com.quantchi.authority.sqlparser.SQLModify;
import com.quantchi.sqlanalysis.v1.PermissionParser;
import com.quantchi.sqlanalysis.model.permission.PermissionResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class SqlAspect {

	private static final Logger logger = LoggerFactory.getLogger(SqlAspect.class);
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private AuthorityDetailService authorityDetailService;

	@Autowired
	SysPermissionInitService sysPermissionInitService;

	public SqlAspect(){
		System.out.print("******SqlAspect is loading*****");
	}

	@Pointcut("execution(public * com.quantchi.intelquery.*.*.execsql*(..))")
	public void myMethod(){}

	@Around("myMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		logger.info("修改前SQL: " + args[0].toString());
		args[0] = modifySqlByDataAuth(args[0].toString());
		logger.info("修改后SQL: " + args[0].toString());
		Object retVal = pjp.proceed(args);
		return retVal;
	}

	private String modifySqlByDataAuth(String sql){

		RowPermission rowPermission = new RowPermission();
		ColumnPermission columnPermission = new ColumnPermission();
		// 通过ThreadLocal获取用户角色，ThreadLocal在doGetAuthorizationInfo（）中设置。
		List<String> roleIdList = RoleListContext.getRoles();

		if(roleIdList != null && roleIdList.size() > 0){
//			for(String roleId:roleIdList){
//				setDataPermission(Integer.parseInt(roleId),rowPermission,columnPermission);
//			}
			setDataPermission(roleIdList, rowPermission,columnPermission);
		}

		PermissionResult permissionResult = new PermissionParser().parse(sql, rowPermission.getRowPermissionJson(), columnPermission.getColumnPermissionJson());

        SQLModify sqlModify = new SQLModify(permissionResult.getLimitedFields(), permissionResult.getSql());

		return sqlModify.getSQL();
	}

	private void setDataPermission(List<String> roleIdList,RowPermission rowPermission,ColumnPermission columnPermission) {
		for(String roleId:roleIdList){
            String authByRoleId = authorityService.getRoleAuthDetail(Integer.parseInt(roleId));

            JSONObject roleAuthJson = JSONObject.parseObject(authByRoleId);
            JSONArray roleAuthJsonArray = roleAuthJson.getJSONArray("data");

            Map<String, Object> requestMap = new HashMap<>();
            //遍历权限，将所有权限对应的行列规则加到Permission中
            for(int i = 0;i < roleAuthJsonArray.size();i++) {
                String authType = roleAuthJsonArray.getJSONObject(i).getString("c_authtype");

                if("1".equals(authType)){// authType == "1" 为数据权限
                    Integer authId = roleAuthJsonArray.getJSONObject(i).getInteger("l_authid");
                    // 取行权限
                    requestMap.put("l_authid", authId);
                    String authTable = authorityDetailService.getAuthdetail(requestMap);
                    JSONObject authTableJson = JSONObject.parseObject(authTable);

                    String dataType = authTableJson.getJSONObject("data").getJSONObject("authiorty").getString("l_datatype");
                    JSONArray authTableJsonArray = authTableJson.getJSONObject("data").getJSONArray("authDetail");

                    if(authTableJsonArray != null && authTableJsonArray.size() > 0){
                        if("3".equals(dataType)) {// 行权限
                            rowPermission.addJsonRowRule(authTableJsonArray);
                        }else if("2".equals(dataType)) {// 列权限
                            columnPermission.addJsonColumnRule(authTableJsonArray);
                        }
                    }
                }
            }
        }
	}

}

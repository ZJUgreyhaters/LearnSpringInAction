package com.quantchi.authority.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import com.quantchi.authority.serviceImpl.AuthorityServiceImpl;
import com.quantchi.authority.shiro.MyRealm;
import com.quantchi.authority.shiro.SysPermissionInitService;
import com.quantchi.authority.sqlparser.ColumnPermission;
import com.quantchi.authority.sqlparser.IdealSQLGen;
import com.quantchi.authority.sqlparser.RowPermission;
import com.quantchi.sqlanalysis.PermissionParser;
import com.quantchi.sqlanalysis.model.permission.PermissionResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
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
		args[0] = modifySqlByDataAuth(args[0].toString());
		Object retVal = pjp.proceed(args);
		return retVal;
	}



	private List<String> getRoles(){
		Subject subject = SecurityUtils.getSubject();
		//String realname = subject.getPrincipals().getRealmNames().iterator().next();
		RealmSecurityManager securityManager =
						(RealmSecurityManager) SecurityUtils.getSecurityManager();
		MyRealm shiroRealm =  (MyRealm)securityManager.getRealms().iterator().next();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("-1", "");
		Cache<Object, AuthorizationInfo> authCache = shiroRealm.getAuthorizationCache();
		return  null;
	}

	private String modifySqlByDataAuth(String sql){

		//getRoles();

		RowPermission rowPermission = new RowPermission();
		ColumnPermission columnPermission = new ColumnPermission();
		//Integer roleId = 48;

		List<String> roleIdList = sysPermissionInitService.getRolesFromDB();
		for(String roleId:roleIdList){
			setDataPermission(Integer.parseInt(roleId),rowPermission,columnPermission);
		}

		PermissionResult permissionResult = new PermissionParser().parse(sql, rowPermission.getRowPermissionJson(), columnPermission.getColumnPermissionJson());

		Set<String> limit = permissionResult.getLimitedFields();
		IdealSQLGen idealSQLGen = new IdealSQLGen(permissionResult.getLimitedFields(), permissionResult.getSql());

		String re = idealSQLGen.getIdealSQL();
		re += "";
		return idealSQLGen.getIdealSQL();
	}

	private void setDataPermission(Integer roleId,RowPermission rowPermission,ColumnPermission columnPermission){
		String authByRoleId = authorityService.getRoleAuthDetail(roleId);

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

				if(dataType.equals("3")) {// 行权限

					for(int j = 0;j < authTableJsonArray.size();j++) {
						String tableName = authTableJsonArray.getJSONObject(j).getString("c_tablename");
						String filterCondition = authTableJsonArray.getJSONObject(j).getString("c_fiter");
						rowPermission.addSimpleRowRule(tableName, filterCondition);
					}

				}else if(dataType.equals("2")) {// 列权限

					for(int k = 0;k < authTableJsonArray.size();k++) {
						String tableName = authTableJsonArray.getJSONObject(k).getString("c_tablename");
						String filterColumn = authTableJsonArray.getJSONObject(k).getString("c_column");
						columnPermission.addSimpleColumnRule(tableName, filterColumn);
					}

				}else if(dataType.equals("1")) {

				}
			}
		}
	}

}

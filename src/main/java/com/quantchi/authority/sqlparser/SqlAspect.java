package com.quantchi.authority.sqlparser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.mapper.AuthorityRoleMapper;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import com.quantchi.authority.serviceImpl.AuthorityServiceImpl;
import com.quantchi.sqlanalysis.PermissionParser;
import com.quantchi.sqlanalysis.model.permission.PermissionResult;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.JoinPoint;
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
	private AuthorityServiceImpl authorityService;

	@Autowired
	private AuthorityDetailService authorityDetailService;

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

	private String modifySqlByDataAuth(String sql){

		RowPermission rowPermission = new RowPermission();
		ColumnPermission columnPermission = new ColumnPermission();
		Integer roleId = 48;

		String authByRoleId = authorityService.getRoleAuthDetail(roleId);

		JSONObject roleAuthJson = JSONObject.parseObject(authByRoleId);
		JSONArray roleAuthJsonArray = roleAuthJson.getJSONArray("data");

		Map<String, Object> requestMap = new HashMap<>();
		//遍历权限，将所有权限对应的行列规则加到Permission中
		for(int i = 0;i < roleAuthJsonArray.size();i++) {
			String authType = roleAuthJsonArray.getJSONObject(i).getString("c_authtype");

			if(authType.equals("1")){// authType == "1" 为数据权限
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

		PermissionResult permissionResult = new PermissionParser().parse(sql, rowPermission.getRowPermissionJson(), columnPermission.getColumnPermissionJson());

		Set<String> limit = permissionResult.getLimitedFields();
		IdealSQLGen idealSQLGen = new IdealSQLGen(permissionResult.getLimitedFields(), permissionResult.getSql());

		String re = idealSQLGen.getIdealSQL();
		re += "";
		return idealSQLGen.getIdealSQL();
	}

}

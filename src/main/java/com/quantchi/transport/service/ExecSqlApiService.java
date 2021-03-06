package com.quantchi.transport.service;

import com.alibaba.fastjson.JSONArray;
import com.quantchi.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.quantchi.tianshu.common.JdbcPool;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExecSqlApiService {
	private static final Logger logger = LoggerFactory.getLogger(ExecSqlApiService.class);

	@Autowired
	@Qualifier("hiveJdbcPool")
	private JdbcPool jdbcPool;

	//public Map<String, Object> execsql(JdbcPool jdbcPool, String sql) {
	public Map<String, Object> execsql(String sql) throws Exception {
		Map<String, Object> _ret = new HashMap<String, Object>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			Connection connection = jdbcPool.getConnection();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			JSONArray _res = Util.convertResultSetIntoJSON(rs);
			_ret.put("data", _res);
			_ret.put("total", _res.size());
		} catch (Exception e) {
			logger.error("Failed to exec  sql, " + sql, e);
			//_ret.put("error",e.getMessage());
			throw new Exception(e.getMessage());
		}
		return _ret;
	}
}

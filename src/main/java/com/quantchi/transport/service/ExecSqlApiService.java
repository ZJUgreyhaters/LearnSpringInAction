package com.quantchi.transport.service;

import com.quantchi.common.util;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.quantchi.tianshu.common.JdbcPool;
import org.apache.hive.service.cli.HiveSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExecSqlApiService {
	private static final Logger logger = LoggerFactory.getLogger(ExecSqlApiService.class);



	public Map<String, Object> execsql(JdbcPool jdbcPool, String sql) {
		Map<String, Object> _ret = new HashMap<String, Object>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			Connection connection = jdbcPool.getConnection();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			JSONArray _res = util.convertResultSetIntoJSON(rs);
			_ret.put("data", _res);
			_ret.put("total", _res.length());
		} catch (Exception e) {
			logger.error("Failed to exec  sql, " + sql, e);
//			throw new SQLException("Failed to exec  sql:" + sql);
		}
		return _ret;
	}
}

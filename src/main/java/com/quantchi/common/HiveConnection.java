package com.quantchi.common;

import com.quantchi.tianshu.common.JdbcPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 49537 on 2018/6/13.
 */
public class HiveConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(HiveConnection.class);

  public static List<Map<String, Object>> selectHive(String sql, JdbcPool jdbcPool) {
    List<Map<String, Object>> list = new LinkedList<>();
    try (Connection conn = jdbcPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      ResultSetMetaData md = rs.getMetaData();
      int columnCount = md.getColumnCount(); // 获得列数
      while (rs.next()) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 1; i <= columnCount; i++) {
          String columnName;
          if (md.getColumnName(i).contains(".")) {
            String[] split = md.getColumnName(i).split(".");
            columnName = split[1].toString();
          } else {
            columnName = md.getColumnName(i).toString();
          }
          Object columnValue = rs.getObject(i);
          if (columnValue == null) {
            columnValue = "null";
          }
          if (columnName.equals(columnValue)) {
            break;
          }
          map.put(columnName, columnValue);
        }
        if (map.size() == 0) {
          continue;
        }
        list.add(map);
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.info("sql error", e);
      return exception();
    }
  }

  public static ResultSet selectHiveWithRs(String sql, JdbcPool jdbcPool) {
		/*Connection conn = null;
		PreparedStatement pstmt = null;
  	ResultSet rs = null;*/
  	try (Connection conn = jdbcPool.getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
      return rs;
    } catch (SQLException e) {
      LOGGER.info("sql error", e);
      return null;
    }
  }

  public static int elseHive(String sql, JdbcPool jdbcPool) {
    int rs;
    try (Connection conn = jdbcPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      rs = pstmt.executeUpdate();
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.info("sql error", e);
      return -500;
    }
  }

  public static List<Map<String, Object>> exception() {
    List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> mapException = new HashMap<>();
    mapException.put("msg", "select error");
    list.add(mapException);
    return list;
  }
}

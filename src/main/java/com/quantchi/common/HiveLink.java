package com.quantchi.common;

import com.quantchi.tianshu.common.JdbcPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;

/**
 * Created by 49537 on 2018/6/13.
 */
public class HiveLink {


  public static List<Map<String, Object>> selectHive(String sql, JdbcPool jdbcPool) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String, Object>> list = new LinkedList<>();
    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      ResultSetMetaData md = rs.getMetaData();
      int columnCount = md.getColumnCount(); // 获得列数
      while (rs.next()) {
        Map<String, Object> map = new LinkedHashMap();
        for (int i = 1; i <= columnCount; i++) {
          String columnName ;
          if (md.getColumnName(i).contains(".")) {
            String[] split = md.getColumnName(i).split(".");
            columnName=split[1].toString();
          }else{
            columnName=md.getColumnName(i).toString();
          }
          Object columnValue = rs.getObject(i);
          if (columnValue == null){
            columnValue = "null";
          }
          if(columnName.equals(columnValue)){
            break;
          }
          map.put(columnName,columnValue);
        }
        if(map.size()==0){
          continue;
        }
        list.add(map);
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
      return exception();
    } catch (Exception e) {
      e.printStackTrace();
      return exception();
    } finally {
      if (rs != null) { // 关闭记录集
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (pstmt != null) { // 关闭声明
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) { // 关闭连接对象
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static int elseHive(String sql, JdbcPool jdbcPool) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    int rs;
    try {
      conn = jdbcPool.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeUpdate();
     return rs;
    } catch (SQLException e) {
      e.printStackTrace();
      return -500;
    } catch (Exception e) {
      e.printStackTrace();
      return -500;
    } finally {
      if (pstmt != null) { // 关闭声明
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) { // 关闭连接对象
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static List<Map<String, Object>> exception(){
    List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> mapException = new HashedMap();
    mapException.put("msg","select error");
    list.add(mapException);
    return list;
  }
}

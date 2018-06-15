package com.quantchi.metadatamgr.extract;

import com.quantchi.metadatamgr.data.DSMetaInfo;
import com.quantchi.metadatamgr.data.FieldEntity;
import com.quantchi.metadatamgr.data.HiveMetaInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HiveExtractImp {

    DSMetaInfo dsMetaInfo;
    public HiveExtractImp(DSMetaInfo ds){
        this.dsMetaInfo = ds;

    }

    //连接测试 成功返回true 失败返回false
    public static boolean connectionTest(String address,String port,String account,String password){

        String driverName = "org.apache.hive.jdbc.HiveDriver";

        try{
            Class.forName(driverName);

            String url = "jdbc:hive2://" + address + ":" + port;
            Connection conn = DriverManager.getConnection(url,account,password);
            if(!conn.isClosed()){
                conn.close();
                return true;
            }


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return false;
    }


    //返回一个mysql连接
    private Connection getMysqlConnection(){

        HiveMetaInfo hiveMetaInfo = dsMetaInfo.getHiveMetaInfo();
        String url = hiveMetaInfo.getMysqlUrl();
        String username = hiveMetaInfo.getMysqlUser();
        String password = hiveMetaInfo.getMysqlPass();

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;

    }
    //返回所有的库
    public List<String> getDatabases(){
        List<String> dbList = new ArrayList<>();

        Connection conn = getMysqlConnection();

        String sql = "SELECT `NAME` FROM DBS";

        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                dbList.add(resultSet.getString("NAME"));
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dbList;
    }

    //    根据库名返回库所有的表
    public List<String> getTables(String database){
        List<String> tblList = new ArrayList<>();

        Connection conn = getMysqlConnection();

        String sql = "SELECT a.TBL_NAME FROM TBLS a, DBS b WHERE a.DB_ID = b.DB_ID AND b.`NAME` = '" + database + "'";

        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                tblList.add(resultSet.getString("TBL_NAME"));
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return tblList;
    }

    //    根据库名和表名返回字段信息
    public List<FieldEntity> getFields(String database, String table){
        List<FieldEntity> fieldBeanList = new ArrayList<>();

        Connection conn = getMysqlConnection();

        String sql = "select d.* from DBS a, TBLS b, SDS c, COLUMNS_V2 d where a.DB_ID = b.DB_ID AND b.SD_ID = c.SD_ID AND c.CD_ID = d.CD_ID AND a.`NAME` = '" + database + "' AND b.TBL_NAME = '" + table + "'";

        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                FieldEntity field = new FieldEntity();
                field.setName(resultSet.getString("COLUMN_NAME"));
                field.setType(resultSet.getString("TYPE_NAME"));
                fieldBeanList.add(field);
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return fieldBeanList;
    }

}

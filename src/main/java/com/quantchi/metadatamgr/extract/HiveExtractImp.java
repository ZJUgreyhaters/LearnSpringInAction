package com.quantchi.metadatamgr.extract;

import com.quantchi.metadatamgr.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HiveExtractImp {

    DSMetaInfo dsMetaInfo;
    public HiveExtractImp(DSMetaInfo ds){
        this.dsMetaInfo = ds;

    }
    public HiveExtractImp(){}

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


    public static boolean connectionMysqlTest(String url,String username,String password){

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            if(!conn.isClosed()){
                conn.close();
                return true;
            }
        }catch (Exception e){
            return false;
        }

        return false;
    }

    //返回一个mysql连接
    private Connection getMysqlConnection() throws Exception{

        HiveMetaInfo hiveMetaInfo = dsMetaInfo.getHiveMetaInfo();
        String url = hiveMetaInfo.getMysqlUrl();
        String username = hiveMetaInfo.getMysqlUser();
        String password = hiveMetaInfo.getMysqlPass();

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            //e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return conn;

    }
    //返回所有的库
    public List<String> getDatabases() throws Exception{
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
    public List<String> getTables(String database,String keyword) throws Exception{
        List<String> tblList = new ArrayList<>();

        Connection conn = getMysqlConnection();

        String sql = "SELECT a.TBL_NAME FROM TBLS a, DBS b WHERE a.DB_ID = b.DB_ID AND b.`NAME` = '" + database + "'";
        if(keyword != null)
            sql = "SELECT a.TBL_NAME FROM TBLS a, DBS b WHERE a.DB_ID = b.DB_ID AND b.`NAME` = '" + database + "' AND a.TBL_NAME like '%"+keyword+"%'";


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
    public List<FieldEntity> getFields(String database, String table) throws Exception{
        List<FieldEntity> fieldBeanList = new ArrayList<>();

        Connection conn = getMysqlConnection();

        String sql = "select d.* from DBS a, TBLS b, SDS c, COLUMNS_V2 d where a.DB_ID = b.DB_ID AND b.SD_ID = c.SD_ID AND c.CD_ID = d.CD_ID AND a.`NAME` = '" + database + "' AND b.TBL_NAME = '" + table + "' ORDER BY d.INTEGER_IDX";

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

    //根据库名，表名获取相应表的字段主外键信息
    public Set<KeyInfo> getKeyInfo(String database,String[] tables) throws Exception{
        Set<KeyInfo> keyInfoList = new HashSet<>();

        int bound = 5;
        int num = tables.length;

        List<TableEntity> tableEntityList = new ArrayList<>();

        //获取表和字段信息
        for(int i = 0;i < num;i ++){
            List<FieldEntity> fieldEntityList = getFields(database,tables[i]);
            TableEntity tableEntity = new TableEntity(tables[i],fieldEntityList);
            tableEntityList.add(tableEntity);
        }

        for(int i = 0;i < num;i ++){
            TableEntity tableNow = tableEntityList.get(i);
            for(int j = 0;j < tableNow.getFieldEntityList().size() && j < bound;j ++){
                String field = tableNow.getFieldEntityList().get(j).getName();
                for(int k = 0;k < num;k ++){
                    if (k == i)
                        continue;
                    TableEntity tableOther = tableEntityList.get(k);
                    List<FieldEntity> fieldEntityList = tableOther.getFieldEntityList();
                    for(int l = 0;l < tableOther.getFieldEntityList().size() && l < bound;l ++){
                        if(field.equals(tableOther.getFieldEntityList().get(l).getName())) {
                            if(j == 1){
                                KeyInfo keyInfo = new KeyInfo(tableNow.getTblName(),field,"PK",null);
                                keyInfoList.add(keyInfo);
                            }
                            else if(l == 1){
                                KeyInfo keyInfo = new KeyInfo(tableNow.getTblName(),field,"FK",tableOther.getTblName());
                                keyInfoList.add(keyInfo);
                            }
                        }
                    }//end for l
                }//end for k
            }//end for j
        }//end for i

        return keyInfoList;
    }

}



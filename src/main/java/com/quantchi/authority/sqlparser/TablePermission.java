package com.quantchi.authority.sqlparser;

import java.util.List;

public class TablePermission {
    /*
    表名
     */
    private String tableName;
    /*
    表是否可读写
    */
    protected boolean isEnabled = true;
//    /*
//    列权限
//     */
//    private List<String> columnRule = new ArrayList<String>();
//    /*
//    行权限
//     */
//    private List<String> rowRule = new ArrayList<String>();

    TablePermission() {

    }

    public TablePermission(String tableName,boolean isEnabled,List<String> columnRule,List<String> rowRule){
        this.tableName = tableName;
        this.isEnabled = isEnabled;
//        this.columnRule = columnRule;
//        this.rowRule =rowRule;
    }

    public String getTableName(){
        return this.tableName;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    public boolean getIsEnable(){
        return this.isEnabled;
    }

    public void setIsEnable(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

//    public List<String> getColumnRule() {
//        return columnRule;
//    }
//
//    public void setColumnRule(List<String> newColumnRule){
//        this.columnRule = newColumnRule;
//    }
//
//    public List<String> getRowRule(){
//        return this.rowRule;
//    }
//
//    public void setRowRule(List<String> newRoeRule){
//        this.rowRule = newRoeRule;
//    }
}

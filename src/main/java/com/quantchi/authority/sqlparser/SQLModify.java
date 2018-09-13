package com.quantchi.authority.sqlparser;

import java.util.Set;

/**
 * @ClassName: SQLModify
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/13 17:09
 * @Version 1.0.0
 **/

public class SQLModify {
    private Set<String> limitField;
    private String toBeModifiedSQL;
    private String idealSQL;

    public SQLModify(){}

    public SQLModify(Set<String> limitField,String toBeModifiedSQL){
        this.limitField = limitField;
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setToBeModifiedSQL(String toBeModifiedSQL){
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setLimitField(Set<String> limitField) {
        this.limitField = limitField;
    }




}

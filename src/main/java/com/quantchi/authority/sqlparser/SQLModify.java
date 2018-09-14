package com.quantchi.authority.sqlparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @ClassName: SQLModify
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/13 17:09
 * @Version 1.0.0
 **/

public class SQLModify {
    private  static final Logger logger = LoggerFactory.getLogger(SQLModify.class);
    
    private Set<String> limitField;
    private String toBeModifiedSQL;

    public SQLModify(){}

    public SQLModify(Set<String> limitField, String toBeModifiedSQL) {
        this.limitField = limitField;
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setToBeModifiedSQL(String toBeModifiedSQL) {
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setLimitField(Set<String> limitField) {
        this.limitField = limitField;
    }

    public String getSQL() {

        if(limitField != null && limitField.size() > 0){
            for(String field: limitField) {
                int toIndex = toBeModifiedSQL.toLowerCase().indexOf(field);
                String sub = toBeModifiedSQL.substring(toIndex + field.length());
                String realField = toBeModifiedSQL.substring(toIndex, toIndex + field.length());

                if(sub.matches("\\s+(as|AS|As|aS)\\s+[\\s\\S]*")) {
                    toBeModifiedSQL = toBeModifiedSQL.replace(realField, "\"***\"");
                }else {
                    toBeModifiedSQL = toBeModifiedSQL.replace(realField,"\"***\" as " + realField);
                }
            }
        }

        return toBeModifiedSQL;
    }

}

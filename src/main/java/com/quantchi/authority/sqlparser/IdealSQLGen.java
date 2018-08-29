package com.quantchi.authority.sqlparser;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import java.util.List;
import java.util.Set;

public class IdealSQLGen {
    private Set<String> limitField;
    private String toBeModifiedSQL;
    private String idealSQL;

    IdealSQLGen(){

    }
    public IdealSQLGen(Set<String> limitField,String toBeModifiedSQL){
        this.limitField = limitField;
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setToBeModifiedSQL(String toBeModifiedSQL){
        this.toBeModifiedSQL = toBeModifiedSQL;
    }

    public void setLimitField(Set<String> limitField) {
        this.limitField = limitField;
    }

    public String getIdealSQL() {
        //todo
        SQLCharExpr xinhao = new SQLCharExpr("***");//'***'
        Set<String> tmpLimitedField = this.limitField;
        idealSQL = this.toBeModifiedSQL;

        SQLStatementParser parser = new SQLStatementParser(toBeModifiedSQL);
        SQLStatement statement = parser.parseStatement();
        if(statement instanceof SQLSelectStatement){
            SQLSelectStatement selectStatement = (SQLSelectStatement) statement;
            SQLSelect select = selectStatement.getSelect();
            SQLSelectQuery selectQuery = select.getQuery();
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) selectQuery;

            List<SQLSelectItem> selectItems = sqlSelectQueryBlock.getSelectList();
            //遍历每一个selectItem
            for(int i = 0;i < selectItems.size();i++){
                //有 as 别名
                if(selectItems.get(i).getAlias() != null){
                    String alias = selectItems.get(i).getAlias().toLowerCase();
                    //别名在不可见的列中
                    if(tmpLimitedField.contains(alias)) {
                        SQLSelectItem exchange = new SQLSelectItem(xinhao,selectItems.get(i).getAlias());
                        selectItems.set(i,exchange);
                        tmpLimitedField.remove(alias);
                    }
                }
                else{//没有 as 别名
                    SQLExpr tmpExpr = selectItems.get(i).getExpr();
                    if(tmpExpr instanceof SQLIdentifierExpr){
                        SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) tmpExpr;
                        String field = identifierExpr.getName().toLowerCase();
                        if(tmpLimitedField.contains(field)){
                            SQLSelectItem exchange = new SQLSelectItem(xinhao,identifierExpr.getName());
                            selectItems.set(i,exchange);
                            tmpLimitedField.remove(field);
                        }
                    }
                    else if(tmpExpr instanceof SQLPropertyExpr){
                        SQLPropertyExpr propertyExpr = (SQLPropertyExpr) tmpExpr;
                        String field = propertyExpr.getName().toLowerCase();
                        if(tmpLimitedField.contains(field)){
                            SQLSelectItem exchange = new SQLSelectItem(xinhao,propertyExpr.getName());
                            selectItems.set(i,exchange);
                            tmpLimitedField.remove(field);
                        }
                    }
                    else{
                        System.out.println("UnSupported selectItem in Class.Method IdealSQLGen.get");
                    }
                }
            }

        }
        else{
            System.out.println("Unsupportted sql clause.(In Class.Method  IdealSQLGen.getIdealSQL())");
        }
        idealSQL = statement.toString();
        return idealSQL;
    }
}

package com.quantchi.authority.sqlparser;

import com.alibaba.fastjson.JSON;
import com.quantchi.sqlanalysis.v1.PermissionParser;
import com.quantchi.sqlanalysis.model.permission.PermissionResult;

/**
 * @ClassName: Parser
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/8/28 18:02
 * @Version 1.0.0
 **/

public class Parser {

    public static void main(String[] args){

        String[] testSql = new String[10];
        String[] table = new String[10];
        testSql[8] = "Select s.name, s.age from student as s where s.major = 'Math'";
        testSql[1] = "select s.id, t.name, t.salary  from (select name, salary, id from teacher) as t left outer join student as s on s.id = t.id";
        testSql[2] = "select t.name as namess, s.name from teacher as t , student as s where s.age > 10";
        testSql[3] = "select t.name as namess, s.name from teacher as t , student as s, admin as a where s.age > 10";
        testSql[4] = "select s.name as name, t.id as namee from teacher as t, student as s";
        testSql[5] = "select s.name as name, t.name as namee from (select name from admin) as xx, teacher as t, student as s";
        testSql[6] = " select x.init_date,x.customer_no,a.customer_name,x.occur_balance,\n" +
                "        x.occur_balance/sum(x.occur_balance) over(partition by x.init_date,x.customer_no) as v_sumcustbal , \n" +
                "        x.occur_balance/sum(x.occur_balance) over(partition by x.init_date,x.branch_no) as v_sumbrabal\n" +
                " from fact_cust_compact_detail x \n" +
                " left join dim_branch t on t.branch_no=x.branch_no\n" +
                " left join dim_customer a on a.customer_no = x.customer_no\n" +
                " where x.init_date >=   '20180101' \n" +
                " and x.init_date<= '20180131'  ";
        testSql[7] = "select sum(id) as sum_id from teacher";
        testSql[0] = "select a from student";

        table[0] = "student";
        table[1] = "teacher";
        table[2] = "admin";
        table[3] = "dim_branch";

        //行权限
        RowPermission rowPermission = new RowPermission();
        //列权限
        ColumnPermission columnPermission = new ColumnPermission();
        //通过addSimpleColumnRule()方法添加一条不可见列的权限规则，参数为表名-对应的一个字段
        columnPermission.addSimpleColumnRule(table[0],"age");
        columnPermission.addSimpleColumnRule(table[0],"name");
        columnPermission.addSimpleColumnRule(table[1],"id");
        columnPermission.addSimpleColumnRule(table[1],"salary");
        columnPermission.addSimpleColumnRule(table[2],"name");
        columnPermission.addSimpleColumnRule(table[3],"occur_balance");
        //columnPermission.addSimpleColumnRule(table[3], "branch_no");
        //通过addSimpleRowRule()方法添加一条行规则
        rowPermission.addSimpleRowRule(table[0],"id>10");
        rowPermission.addSimpleRowRule(table[1],"department in ('Math','China')");
        rowPermission.addSimpleRowRule(table[2],"age >= 15");
        rowPermission.addSimpleRowRule(table[3], "id > 10");

        for(int i = 0;i < 9;i++){
            System.out.println("@sql测试语句" + i + "-------------------");

            PermissionResult Test = new PermissionParser().parse(testSql[i], rowPermission.getRowPermissionJson(), columnPermission.getColumnPermissionJson());
            System.out.println("++++++JSON+++++++\n" + JSON.toJSONString(Test) + "\n+++++++++++++++++\n最后输出的sql:");
            //Test.getLimitedFields()获得需要修改的列信息，Test.getSql()得到已经加入行权限的sql语句
            IdealSQLGen sqlGen = new IdealSQLGen(Test.getLimitedFields(), Test.getSql());
            //getIdealSQL()方法获得最终的sql
            System.out.println(sqlGen.getIdealSQL() + "\n+++++++++++++++++\n\n");
        }
        //
    }
}

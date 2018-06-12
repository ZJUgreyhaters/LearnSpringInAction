package com.quantchi.customer.serviceImpl;

import com.quantchi.customer.service.CustomerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/11.
 */
@Service("CustomerGroupService")
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    @Qualifier("dataSource")
    private DataSource jdbcPool;

    @Override
    public Map<String, Object> listCustomerGroupCriterias(int pagesize, int pageIndex) {
        try{
            String sql = "SELECT * FROM term_logic_temp";
            Connection connection = jdbcPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = df.format(new Date());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}

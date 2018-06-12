package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.customer.mapper.ConditionGroupMapper;
import com.quantchi.customer.pojo.ConditionGroup;
import com.quantchi.customer.service.ConditionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConditionGroupServiceImpl implements ConditionGroupService {

    @Autowired
    private ConditionGroupMapper conditionGroupMapper;

    @Override
    public List<Object> listCustomerGroupCriterias(Integer page_size, Integer page) throws SQLException {

        PageHelper.startPage(page, page_size);
        // 执行查询
        List<Object> list = conditionGroupMapper.listCustomerGroupCriterias();
        // 取分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(list);

        // 返回处理结果
        Map<String,Object> result = new HashMap<String,Object>();
        // 获取总条数
        result.put("Total",pageInfo.getTotal());
        // 结果rows数据
        result.put("Rows",list);
        return pageInfo.getList();
    }

    @Override
    public void createCustomerGroupCriteria(Map<String, Object> map) throws SQLException {
        conditionGroupMapper.createCustomerGroupCriteria(map);
    }
}

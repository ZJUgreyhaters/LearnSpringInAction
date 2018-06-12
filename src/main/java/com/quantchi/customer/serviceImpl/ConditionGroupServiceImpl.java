package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.customer.mapper.ConditionGroupMapper;
import com.quantchi.customer.pojo.ConditionGroup;
import com.quantchi.customer.service.ConditionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public Map<String, Object> findCustomerGroup(String cunstomerGroupId) {
        ConditionGroup conditionGroup = null;
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        conditionGroup = conditionGroupMapper.findCustomerGroup(cunstomerGroupId);
        map.put("cust_group_name",conditionGroup.getCust_group_name());
        map.put("create_user_id",conditionGroup.getCreate_user_id());
        String condition_desc = conditionGroup.getCondition_desc();
        String condition_desc_id = conditionGroup.getCondition_desc_id();
        String[] condition_descs = condition_desc.split("\\|");
        String[] condition_desc_ids = condition_desc_id.split("\\|");
        for(int i=0; i<condition_descs.length;i++){
            Map<String,Object> conditionMap = new HashMap<>();
            String[] desc_id = condition_desc_ids[i].split(":");
            conditionMap.put("id",desc_id[1].split(",")[0]);
            conditionMap.put("type",desc_id[1].split(",")[1]);
            String[] desc = condition_descs[i].split(":");
            conditionMap.put("name",desc[0]);
            conditionMap.put("value",desc[1]);
            list.add(conditionMap);
        }

        map.put("CustomerGroupCriteria",list);
        return map;
    }
}

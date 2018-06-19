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
    public Map<String,Object> listCustomerGroupCriterias(Integer page_size, Integer page) throws SQLException {

        PageHelper.startPage(page, page_size);
        // 执行查询
        List<Object> list = conditionGroupMapper.listCustomerGroupCriterias();
        // 取分页信息
        PageInfo<Object> pageInfo = new PageInfo<>(list);

        // 返回处理结果
        Map<String,Object> result = new HashMap<String,Object>();
        // 获取总条数
        result.put("total",pageInfo.getTotal());

        List<Object> pageList = pageInfo.getList();
        List<Object> resultList = new ArrayList<>();

        for(int i=0; i < pageList.size(); i++){
            Map<String, Object> conditionGroupMap = (Map<String, Object>)pageList.get(i);
//            conditionGroupMap.put("cust_group_id", conditionGroup.getCust_group_id());
//            conditionGroupMap.put("cust_group_name", conditionGroup.getCust_group_name());
//            conditionGroupMap.get("condition_desc", conditionGroup.getCondition_desc().replace("[|]",""));
            String desc = conditionGroupMap.get("condition_desc").toString().replaceAll("\\[|\\]","");
            conditionGroupMap.put("condition_desc",desc);

            resultList.add(conditionGroupMap);
        }
        result.put("data",resultList);
//        result.put("data",pageInfo.getList());

        return result;
    }

    @Override
    public void createCustomerGroupCriteria(Map<String, Object> map) throws SQLException {
        conditionGroupMapper.createCustomerGroupCriteria(map);
    }

    @Override
    public Map<String, Object> findCustomerGroup(String customerConditionId) {
        ConditionGroup conditionGroup = null;
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try{
            conditionGroup = conditionGroupMapper.findCustomerGroup(customerConditionId);
            map.put("cust_condition_name",conditionGroup.getCust_condition_name());
            map.put("create_user_id",conditionGroup.getCreate_user_id());
            String condition_desc = conditionGroup.getCondition_desc();
            String condition_desc_id = conditionGroup.getCondition_desc_id();
            String[] condition_descs = condition_desc.split("\\|");
            String[] condition_desc_ids = condition_desc_id.split("\\|");
            for(int i=0; i<condition_descs.length;i++){
                Map<String,Object> conditionMap = new HashMap<>();
                String[] desc_id = condition_desc_ids[i].split(":");
                conditionMap.put("id",desc_id[1].split(",")[0]);
                conditionMap.put("type",desc_id[2]);
                String[] desc = condition_descs[i].split(":");
                if(!conditionMap.get("type").equals("value")){

                    conditionMap.put("name",desc[0]);
                    String[] s = desc[1].split(",");
                    conditionMap.put("value",s);
                }else{
                    conditionMap.put("name",desc[0]);
                    conditionMap.put("value",desc[1]);
                }

                List<String> listValue1 = new ArrayList<>();
//                if(conditionMap.get("type").equals("value")){
//                    listValue1.add("杭州");
//                }
//                if(conditionMap.get("type").equals("area")){
//                    listValue1.add("男");
//                    listValue1.add("女");
//                }
//                if(conditionMap.get("type").equals("select")) {
//                    listValue1.add("个人客户");
//                    listValue1.add("机构客户");
//                    listValue1.add("私募");
//                }
                listValue1.add("个人客户");
                listValue1.add("机构客户");
                listValue1.add("私募");


                conditionMap.put("values",listValue1);
                list.add(conditionMap);
            }
            map.put("code",200);
            map.put("msg","查询成功");
            map.put("customer_group_criteria_def",list);
            return map;

        } catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","查询失败，请输入正确id");
            return map;
        }

    }

    @Override
    public Map<String, Object> deleteCustomerGroup(String customerConditionId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            if(conditionGroupMapper.deleteCustomerGroup(customerConditionId) <=0){
                result.put("code",500);
                result.put("msg","当前id已经被删除");
                return result;
            }
            result.put("code",200);
            result.put("msg","删除成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",500);
            result.put("msg","删除失败");
            return result;
        }
    }
}

package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.customer.mapper.CustomerGroupMapper;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/6/11.
 */
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

  @Autowired
  private CustomerGroupMapper mapper;

  @Override
  public Map<String, Object> selectCustomerGroup(CustomerGroup customerGroup, Integer pageIndex,
      Integer pagesize) {

    PageHelper.startPage(pageIndex, pagesize);
    // 执行查询
    List<CustomerGroup> list = mapper.selectCustomerGroup(customerGroup);
    // 取分页信息
    PageInfo<CustomerGroup> pageInfo = new PageInfo<>(list);
    // 返回处理结果
    Map<String,Object> result = new HashMap<String,Object>();
    // 获取总条数
    result.put("Total",pageInfo.getTotal());
    // 结果rows数据
    result.put("Rows",list);
    return result;
  }
}

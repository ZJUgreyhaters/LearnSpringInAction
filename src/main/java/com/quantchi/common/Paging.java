package com.quantchi.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/19.
 */
public class Paging {

  public final static List<Map<String, Object>> pagingPlug(
      List<Map<String, Object>> list, Integer pageSize, Integer pageIndex) {
      int a = (pageIndex-1)*10+1;
      int b = pageIndex*pageSize;
      List<Map<String, Object>> resultList = new ArrayList<>();
    for(int i=a;i<=b;i++){
      resultList.add(list.get(i));
      }
    return resultList;
  }
}

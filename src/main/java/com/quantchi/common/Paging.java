package com.quantchi.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/19.
 */
public class Paging {

  public static <T> Map<String, Object> pagingWithTotal(List<T> list, Integer pageSize, Integer page) {
    List<T> subList = pagingPlug(list, pageSize, page);
    Map<String, Object> map = new HashMap<>(3);
    map.put("total", list.size());
    map.put("data", subList);
    return map;
  }

  public static <T> List<T> pagingPlug(List<T> list, Integer pageSize, Integer page) {
    if (page == null || pageSize == null || list.size() == 0) {
      return list;
    }
    int start = Integer.max((page - 1) * pageSize, 0);
    int end = Integer.min(page * pageSize, list.size());
    return list.subList(start, end);
  }

  public final static boolean judgment(Map<String, Object> map) {
    if (map.get("page") != null && map.get("page").toString().trim().length() > 0) {
      if (map.get("page_size") != null && map.get("page_size").toString().trim().length() > 0){
        return true;
      }
    }
    return false;
  }
}

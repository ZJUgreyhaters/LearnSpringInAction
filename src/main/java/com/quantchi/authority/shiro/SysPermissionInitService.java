package com.quantchi.authority.shiro;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.mapper.AuthorityRoleMapper;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SysPermissionInitService
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/7 15:42
 * @Version 1.0.0
 **/
@Service
public class SysPermissionInitService {
    private Map<String, String> sysPermissionInits = new LinkedHashMap<>();
    @Autowired
    private AuthorityDetailService authorityDetailService;
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityRoleMapper authRoleMapper;

    SysPermissionInitService(){}

    public SysPermissionInitService(Map<String, String> sysPermissionInits){
        this.sysPermissionInits = sysPermissionInits;
    }

    public void setSysPermissionInits(Map<String, String> sysPermissionInits) {
        this.sysPermissionInits = sysPermissionInits;
    }

    public Map<String, String> selectAll(){
        Map<String, Set<Integer>> urlPermItems = new LinkedHashMap<>();

        String funcDetail = authorityDetailService.getFuncDetailList();
        JSONObject funcDetailJSON = JSONObject.parseObject(funcDetail);
        JSONArray funcDetailJSONArray = funcDetailJSON.getJSONArray("data");
        //遍历url-funcDetail
        for(int i = 0;i < funcDetailJSONArray.size();i++) {
            String url = funcDetailJSONArray.getJSONObject(i).getString("c_url");

            Integer funcdetailID = funcDetailJSONArray.getJSONObject(i).getInteger("l_funcdetail");

            Map<String, Object> requestAuth = new HashMap<>();
            requestAuth.put("l_funcdetailid", funcdetailID);
            String authDetail = authorityService.getAuthByFilter(requestAuth);
            JSONObject authDetailJSON = JSONObject.parseObject(authDetail);
            JSONArray authDetailJSONArray = authDetailJSON.getJSONArray("data");

            Set<Integer> roleIdSet = new HashSet<>();
            //Set<String> roleSet = new HashSet<>();
            for(int j = 0;j < authDetailJSONArray.size();j++) {
                Integer authID = authDetailJSONArray.getJSONObject(j).getInteger("l_authid");

                Map<String, Object> requestRole = new HashMap<>();
                requestRole.put("l_authid", authID);
                String roleDetail = authorityService.getRoleByFilter(requestRole);
                JSONObject roleDetailJSON = JSONObject.parseObject(roleDetail);
                JSONArray roleDetailJSONArray = roleDetailJSON.getJSONArray("data");
                for(int k = 0;k < roleDetailJSONArray.size();k++) {
                    Integer roleId = roleDetailJSONArray.getJSONObject(k).getInteger("l_roleid");
                    //String roleName = roleDetailJSONArray.getJSONObject(k).getString("c_rolename");
                    roleIdSet.add(roleId);
                    //roleSet.add(roleName);
                }
            }
            if(urlPermItems.containsKey(url)){
                for(Integer roleItem: urlPermItems.get(url)){
                    roleIdSet.add(roleItem);
                }
            }
            urlPermItems.put(url, roleIdSet);
        }

        for(String urlKey: urlPermItems.keySet()) {
            if(urlKey != null && urlPermItems.get(urlKey).size() > 0) {
                StringBuilder tmp = new StringBuilder();
                Set<Integer> roleItems = urlPermItems.get(urlKey);
                Iterator<Integer> it = roleItems.iterator();

                while (it.hasNext()) {
                    tmp.append(it.next().toString());
                    if(it.hasNext()) {
                        tmp.append(",");
                    }
                }

                String filter = "MyRoleFilter[\"" + tmp + "\"]";
                sysPermissionInits.put(urlKey, filter);
            }
        }

        sysPermissionInits.put("/api/get*", "DeniyAllFilter");
        return this.sysPermissionInits;
    }

    public List<String> getRolesFromDB(){
        List<Map<String, Object>> roleList = authRoleMapper.getAuthRole();
        return roleList.stream().map(i->i.get("l_roleid").toString()).collect(Collectors.toList());
    }
}

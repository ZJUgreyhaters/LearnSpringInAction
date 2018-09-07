package com.quantchi.authority.shiro;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: SysPermissionInitService
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/7 15:42
 * @Version 1.0.0
 **/
@Service
public class SysPermissionInitService {
    private Map<String, String> sysPermissionInits = new HashMap<>();
    @Autowired
    private AuthorityDetailService authorityDetailService;
    @Autowired
    private AuthorityService authorityService;

    SysPermissionInitService(){}

    public SysPermissionInitService(Map<String, String> sysPermissionInits){
        this.sysPermissionInits = sysPermissionInits;
    }

    public void setSysPermissionInits(Map<String, String> sysPermissionInits) {
        this.sysPermissionInits = sysPermissionInits;
    }

    public Map<String, String> selectAll(){
        Map<String, Set<String>> urlPermItems = new HashMap<>();

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

            Set<String> roleSet = new HashSet<>();
            for(int j = 0;j < authDetailJSONArray.size();j++) {
                Integer authID = authDetailJSONArray.getJSONObject(j).getInteger("l_authid");

                Map<String, Object> requestRole = new HashMap<>();
                requestRole.put("l_authid", authID);
                String roleDetail = authorityService.getRoleByFilter(requestRole);
                JSONObject roleDetailJSON = JSONObject.parseObject(roleDetail);
                JSONArray roleDetailJSONArray = roleDetailJSON.getJSONArray("data");
                for(int k = 0;k < roleDetailJSONArray.size();k++) {
                    String roleName = roleDetailJSONArray.getJSONObject(k).getString("c_rolename");
                    roleSet.add(roleName);
                }
            }
            if(urlPermItems.containsKey(url)){
                for(String roleItem: urlPermItems.get(url)){
                    roleSet.add(roleItem);
                }
            }
            urlPermItems.put(url, roleSet);
        }

        for(String urlKey: urlPermItems.keySet()) {
            if(urlKey!=null) {
                StringBuilder tmp = new StringBuilder();
                Set<String> roleItems = urlPermItems.get(urlKey);
                Iterator<String> it = roleItems.iterator();

                while (it.hasNext()){
                    tmp.append(it.next());
                    if(it.hasNext()){
                        tmp.append(", ");
                    }
                }

                String filter = "MyRoleFilter[\"" + tmp + "\"]";
                sysPermissionInits.put(urlKey, filter);
            }

        }

        return this.sysPermissionInits;
    }
}

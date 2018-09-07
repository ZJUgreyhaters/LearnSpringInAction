package com.quantchi.authority.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SysPermissionInit
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/7 15:43
 * @Version 1.0.0
 **/
@Service
public class SysPermissionInit {
    private String url;
    private String role;

    SysPermissionInit(){}

    public SysPermissionInit(String url, String role){
        this.url = url;
        this.role = role;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public String getRole() {
        return role;
    }
}

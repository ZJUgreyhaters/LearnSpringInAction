package com.quantchi.authority.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: MyRoleFilter
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/5 14:14
 * @Version 1.0.0
 **/

public class MyRoleFilter extends AuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
//        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiroLearn.ini");
//
//        SecurityManager securityManager = securityManagerFactory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wbchen", "222");
        Subject subject = getSubject(req, resp);
        subject.login(usernamePasswordToken);
        String[] rolesArray = (String[]) mappedValue;
        if(rolesArray == null || rolesArray.length == 0){
            return true;
        }
        for(int i = 0;i < rolesArray.length; i++) {
            if(subject.hasRole(rolesArray[i])){
                return true;
            }
            System.out.println("--- MyRoleFilter ---" + rolesArray[i]);
        }
        System.out.println("Done.");
        return true;
    }

}

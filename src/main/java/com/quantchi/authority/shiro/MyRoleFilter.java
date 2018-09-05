package com.quantchi.authority.shiro;

import org.apache.shiro.subject.Subject;
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
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        Subject subject = getSubject(req, resp);
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
        return true;
    }

}

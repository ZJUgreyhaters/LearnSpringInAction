package com.quantchi.authority.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final String ROLEID = "roleId";

    private static final Logger logger = LoggerFactory.getLogger(MyRoleFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {

        //不使用session ,由于login不在一个系统里，只能通过cookie传递,暂时认为传递的是userId

        logger.info("URL拦截 by MyRoleFilter");
        Subject subject = SecurityUtils.getSubject();

        String[] rolesArray = (String[]) mappedValue;
        if(rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for(int i = 0;i < rolesArray.length; i++) {
            if(subject.hasRole(rolesArray[i])){
                return true;
            }
        }
        return false;
    }

}

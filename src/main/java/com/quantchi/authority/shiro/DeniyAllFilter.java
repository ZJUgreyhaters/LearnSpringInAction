package com.quantchi.authority.shiro;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: DeniyAllFilter
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/8 16:42
 * @Version 1.0.0
 **/

public class DeniyAllFilter extends AuthorizationFilter {
    private static final Logger logger = LoggerFactory.getLogger(DeniyAllFilter.class);
    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        logger.info("Login Access.");
        return true;
    }

}

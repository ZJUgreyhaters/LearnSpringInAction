package com.quantchi.authority.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: MyUrlFilter
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/4 09:38
 * @Version 1.0.0
 **/

public class MyUrlFilter extends AccessControlFilter {
    private static final Logger logger = LoggerFactory.getLogger(MyUrlFilter.class);
//    @Override
//    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
//        throws Exception {
//        logger.info("--- --- --- >");
//
//        System.out.println("--- --- --- >" + request.toString());
//        return true;
//    }

    // AccessControlFilter
    public boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue){
        String loginUrl = getLoginUrl();
        //System.out.println(mappedValue.toString());
        logger.warn("--- MyUrlFilter --- Method: isAccessAllowed");
        System.out.println("--- MyUrlFilter --- LoginUrl: " + loginUrl);

        return true;
    }

    public boolean onAccessDenied(ServletRequest request,ServletResponse response, Object mappedValue){
        System.out.println("onAccessDenied++");
        return true;
    }

    public boolean onAccessDenied(ServletRequest request, ServletResponse response){
        System.out.println("onAccessDenied--");
        return true;
    }
}

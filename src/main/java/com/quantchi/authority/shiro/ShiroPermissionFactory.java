package com.quantchi.authority.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ClassName: MyShiroFilterFactoryBean
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/3 10:42
 * @Version 1.0.0
 **/

public class ShiroPermissionFactory extends ShiroFilterFactoryBean {
    private static final Logger logger = LoggerFactory.getLogger(ShiroPermissionFactory.class);

    @Autowired
    SysPermissionInitService sysPermissionInitService;

    public static String definition = "";

    @Override
    public void setFilterChainDefinitions(String definitions){
        if(definitions != null){
            definition = definitions;
        }
        logger.info("Default definition is : " + definition);

        Map<String, String> otherChains = sysPermissionInitService.selectAll();
        logger.info("Initial filter chain: " + otherChains.toString());
        setFilterChainDefinitionMap(otherChains);
    }
}

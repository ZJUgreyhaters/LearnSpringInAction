package com.quantchi.authority.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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

    public static String definition = "";



    @Override
    public void setFilterChainDefinitions(String definitions){

        definition = definitions;
        System.out.println("definition -> " + definition);


        // 数据库获取
        Map<String,String> otherChains = getFilterChainFromDataBase();

        //otherChains.put("/api/getRecommendQuery", "MyUrlFilter");

        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        //加上数据库中过滤链
        section.putAll(otherChains);
        for(String s : section.keySet()){
            System.out.println(s);
        }

        setFilterChainDefinitionMap(section);
    }

    public Map<String, String> getFilterChainFromDataBase() {
        Map<String, String> filter = new HashMap<>();

        // 从数据库取出url过滤的规则：[url] [过滤器名称]()

        filter.put("/api/getRecommendQuery", "MyUrlFilter");

        return filter;
    }

}

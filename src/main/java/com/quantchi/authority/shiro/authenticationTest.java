package com.quantchi.authority.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * @ClassName: authenticationTest
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/8/31 15:14
 * @Version 1.0.1
 **/

public class authenticationTest {
    private static final Logger logger = LoggerFactory.getLogger(authenticationTest.class);

    @Test
    public void testLogin(){
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiroLearn.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wbchen", "222");
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException ex){
            //ex.printStackTrace();
        }
        System.out.println("是否通过验证：" + subject.isAuthenticated());
        System.out.println("是否有角色admin：" + subject.hasRole("admin"));
        System.out.println("是否有角色user：" + subject.hasRole("user"));
        System.out.println("是否拥有权限： " + subject.isPermitted("user:add"));
        subject.checkPermission("user:delete");

        subject.logout();
        System.out.println("用户退出" + subject.isAuthenticated());
    }

    @Test
    public void testAuthorization(){
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiroPermission.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wbchen", "222");
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException ex){
            //ex.printStackTrace();
        }
        System.out.println("用户通过登录认证：" + subject.isAuthenticated());

        Boolean isPermittedOne = subject.isPermitted("user:create");
        System.out.println("单个权限认证："+isPermittedOne);

        Boolean isPermittedHome = subject.isPermitted("/home");
        System.out.println("/home权限认证："+isPermittedOne);

        Boolean isPermittedAll = subject.isPermittedAll("user:create", "user:update");
        System.out.println("多个权限认证：" + isPermittedAll);

        Boolean ishasRole = subject.hasRole("role1");
        //Boolean ishasRoleAll = subject.hasAllRoles(Arrays.asList("role1","role2"));
        //System.out.println("单个角色验证："+ishasRole+" | 多个角色验证"+ishasRoleAll);

        Hashtable<String,String> testHashTable = new Hashtable<>();
        testHashTable.put("Tom", "anhui");
        testHashTable.put("Tom", "nice");
        String province = testHashTable.get("Tom");

        System.out.println(province + testHashTable.containsKey("Tom"));

    }
}

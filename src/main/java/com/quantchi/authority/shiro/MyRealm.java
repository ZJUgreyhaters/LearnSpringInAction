package com.quantchi.authority.shiro;

import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import common.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: MyRealm
 * @Description: 自定义数据源
 * @Author: wbchen
 * @Date: 2018/8/31 15:35
 * @Version 1.0.0
 **/

public class MyRealm extends AuthorizingRealm {
    //private Logger log;

    @Override
    protected AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        //authenticationToken为用户输入信息
        String userCode = (String) authenticationToken.getPrincipal();
        String pwd =  new String((char[])authenticationToken.getCredentials());
        System.out.println("pwd -> " + pwd);
        //模拟数据库操作查询用户
        if(!userCode.equals("wbchen")){
            System.out.println("user must be wbchen!");
            return null;
        }
        else{
            System.out.println("Hello, " + userCode);
        }
        // 查询密码为222
        String password="222";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userCode,password,"testRealm");

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //log.info("--- AuthorizationInfo doGetAuthorizationInfo ---");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> role = new HashSet<>();
        role.add("admin");
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.addRole("user");
        System.out.println("Role: " + simpleAuthorizationInfo.getRoles().toString());
        simpleAuthorizationInfo.addStringPermission("user:delete");
        System.out.println("--- doGetAuthorizationInfo ---");
        return simpleAuthorizationInfo;
    }
}

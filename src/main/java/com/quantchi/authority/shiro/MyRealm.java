package com.quantchi.authority.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * @ClassName: MyRealm
 * @Description: 自定义数据源
 * @Author: wbchen
 * @Date: 2018/8/31 15:35
 * @Version 1.0.0
 **/

public class MyRealm extends AuthorizingRealm {
    //private Logger log;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    SysPermissionInitService sysPermissionInitService;

    @Override
    protected AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        //authenticationToken为用户输入信息
        String username = (String) authenticationToken.getPrincipal();

        if(username.equals("liangzhi")) {
            String password = new String((char[])authenticationToken.getCredentials());
            if(password.equals("liangzhi123")) {
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, "myrealm");

                return info;
            }
        }
        return null;
//        String roleId = (String) authenticationToken.getPrincipal();
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(roleId,"","testRealm");
//
//        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //TODO get roleId by userId from db
        /*Object roleId =  principalCollection.getPrimaryPrincipal();
        simpleAuthorizationInfo.addRole(roleId.toString());*/
        //get all roles
        List<String> roleIdList = sysPermissionInitService.getRolesFromDB();
        simpleAuthorizationInfo.addRoles(roleIdList);

        RoleListContext.setRoles(roleIdList);

        logger.info("roleIds: " + roleIdList.toString());
        return simpleAuthorizationInfo;
    }
}

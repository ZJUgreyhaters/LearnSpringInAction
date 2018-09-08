package com.quantchi.authority.shiro;

import com.quantchi.authority.mapper.AuthorityRoleMapper;
import com.quantchi.authority.service.AuthorityService;
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

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
    private AuthorityRoleMapper authRoleMapper;

    @Override
    protected AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        //authenticationToken为用户输入信息
        String roleId = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(roleId,"","testRealm");
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //TODO get roleId by userId from db
        /*Object roleId =  principalCollection.getPrimaryPrincipal();
        simpleAuthorizationInfo.addRole(roleId.toString());*/
        //get all roles
        List<Map<String, Object>> roleList = authRoleMapper.getAuthRole();
        List<String> roleIdList = roleList.stream().map(i->i.get("l_roleid").toString()).collect(Collectors.toList());
        simpleAuthorizationInfo.addRoles(roleIdList);
        logger.info("roleIds: " + roleIdList.toString());
        return simpleAuthorizationInfo;
    }
}

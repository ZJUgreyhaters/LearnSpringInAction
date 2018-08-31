package com.quantchi.authority.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName: MyPermissionRealm
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/8/31 16:24
 * @Version 1.0.0
 **/

public class MyPermissionRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        String userCode=(String)authenticationToken.getPrincipal();
        //模拟数据库操作查询用户
        if(!userCode.equals("wbchen")){
            return null;
        }
        //查询密码为111
        String password="111";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userCode,password,"testRealm");

        return info;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

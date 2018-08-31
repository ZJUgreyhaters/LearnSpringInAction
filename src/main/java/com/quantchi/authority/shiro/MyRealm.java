package com.quantchi.authority.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName: MyRealm
 * @Description: 自定义数据源
 * @Author: wbchen
 * @Date: 2018/8/31 15:35
 * @Version 1.0.0
 **/

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        //authenticationToken为用户输入信息
        String userCode = (String) authenticationToken.getPrincipal();
        //模拟数据库操作查询用户
        if(!userCode.equals("wbchen")){
            return null;
        }
        //查询密码为222
        String password="222";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userCode,password,"testRealm");

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

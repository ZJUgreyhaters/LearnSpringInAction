package com.quantchi.authority.shiro;

import java.util.List;

/**
 * @ClassName: RoleListContext
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/12 16:30
 * @Version 1.0.0
 **/

public class RoleListContext {
    private static ThreadLocal<List<String>> roles = new ThreadLocal<>();

    public static void setRoles(List<String> roleLists) {
        roles.set(roleLists);
    }

    public static void removeRoles() {
        roles.remove();
    }

    public static List<String> getRoles() {
        List<String> reRoles = roles.get();
        return reRoles;
    }
}

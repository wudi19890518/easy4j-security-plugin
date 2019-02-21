package org.easy4j.plugin.security;

import java.util.Set;

/**
 *
 */
public interface Easy4jSecurity {

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    String getPassword(String username);

    /**
     * 获取角色集合
     * @param username
     * @return
     */
    Set<String> getRoleNameSet(String username);

    /**
     * 根据角色名获取权限集合
     * @param roleName 角色名称
     * @return
     */
    Set<String> getPermissonNameSet(String roleName);

}

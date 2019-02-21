package org.easy4j.plugin.security.realm;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.easy4j.plugin.security.Easy4jSecurity;
import org.easy4j.plugin.security.SecurityConstant;
import org.easy4j.plugin.security.credentials.Md5CredentialsMatcher;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Easy4jCustomRealm extends AuthorizingRealm {

    private Easy4jSecurity easy4jSecurity;

    public Easy4jCustomRealm(Easy4jSecurity easy4jSecurity){
        this.easy4jSecurity = easy4jSecurity;
        this.setName(SecurityConstant.REALMS_CUSTOM);
        this.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token == null){
            throw new AuthenticationException("parameter token is null");
        }

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();

        String password = easy4jSecurity.getPassword(username);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();

        simpleAuthenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        simpleAuthenticationInfo.setCredentials(password);

        return simpleAuthenticationInfo;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals == null){
            throw new AuthorizationException("parameter principals is null");
        }

        String username = (String) super.getAvailablePrincipal(principals);

        Set<String> roleNameSet = easy4jSecurity.getRoleNameSet(username);

        Set<String> permissionNameSet = new HashSet<String>();
        if(CollectionUtils.isNotEmpty(permissionNameSet)){
            for(String roleName : roleNameSet){
                Set<String> permissionSet = easy4jSecurity.getPermissonNameSet(roleName);
                permissionNameSet.addAll(permissionSet);
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);

        return authorizationInfo;
    }


}

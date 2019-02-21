package org.easy4j.plugin.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.easy4j.plugin.security.Easy4jSecurity;
import org.easy4j.plugin.security.SecurityConstant;
import org.easy4j.plugin.security.credentials.Md5CredentialsMatcher;

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
            return null;
        }
        return null;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}

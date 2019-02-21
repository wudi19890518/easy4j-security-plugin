package org.easy4j.plugin.security;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.easy4j.plugin.security.realm.Easy4jCustomRealm;
import org.easy4j.plugin.security.realm.Easy4jJdbcRealm;

import java.util.LinkedHashSet;
import java.util.Set;

public class Easy4jSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        setRealms(webSecurityManager);
        setCache(webSecurityManager);
    }

    private void setRealms(WebSecurityManager webSecurityManager){
        String securityRealms = SecurityConfig.getRealms();
        if(StringUtils.isNotEmpty(securityRealms)){
            String[] securityRealmArray = securityRealms.split(",");
            if(ArrayUtils.isNotEmpty(securityRealmArray)){
                Set<Realm> realms = new LinkedHashSet();
                for(String securityRealm : securityRealmArray){
                    if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        addJdbcRealm(realms);
                    }else if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        addCustomRealm(realms);
                    }
                }

                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                realmSecurityManager.setRealms(realms);
            }
        }
    }

    private void addJdbcRealm(Set<Realm> realms){
        Easy4jJdbcRealm jdbcRealm = new Easy4jJdbcRealm();

        realms.add(jdbcRealm);
    }

    private void addCustomRealm(Set<Realm> realms){
        Easy4jSecurity easy4jSecurity = SecurityConfig.getEasy4jSecurity();
        Easy4jCustomRealm easy4jCustomRealm = new Easy4jCustomRealm(easy4jSecurity);
        realms.add(easy4jCustomRealm);
    }

    private void setCache(WebSecurityManager webSecurityManager){

    }
}

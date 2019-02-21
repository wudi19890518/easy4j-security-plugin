package org.easy4j.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.easy4j.framework.helper.DatabaseHelper;
import org.easy4j.plugin.security.SecurityConfig;
import org.easy4j.plugin.security.credentials.Md5CredentialsMatcher;

/**
 *
 */
public class Easy4jJdbcRealm extends JdbcRealm {
    public Easy4jJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}

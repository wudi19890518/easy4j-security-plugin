package org.easy4j.plugin.security;

public interface SecurityConstant {

    String REALMS = "app.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String EASY4J_SECURITY = "app.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "app.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "app.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "app.plugin.security.jdbc.permissions_query";

    String CACHE = "app.plugin.security.cache";
}

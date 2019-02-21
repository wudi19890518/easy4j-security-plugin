package org.easy4j.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 *
 */
public class Easy4jSecurityPlugin implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations", "classpath:easy4j-security.ini");
        // 注册 Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册 Filter
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("Easy4jSecurityFilter", Easy4jSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}

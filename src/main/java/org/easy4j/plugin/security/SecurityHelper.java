package org.easy4j.plugin.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.easy4j.plugin.security.exception.AuthcException;


/**
 *
 */
public class SecurityHelper {

    private static final Logger LOGGER = LogManager.getLogger(SecurityHelper.class);

    /**
     * 登录
     * @param username
     * @param password
     */
    public static void login(String username, String password) throws AuthcException{
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                currentUser.login(token);
            }catch(Exception e){
                LOGGER.error("login failure", e);
                throw new AuthcException(e);
            }
        }
    }

    /**
     * 注销
     */
    public static void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            currentUser.logout();
        }
    }
}

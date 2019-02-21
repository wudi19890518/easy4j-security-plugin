package org.easy4j.plugin.security.aspect;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.easy4j.framework.annotation.Aspect;
import org.easy4j.framework.annotation.Controller;
import org.easy4j.framework.proxy.AbstractProxy;
import org.easy4j.plugin.security.annotation.User;
import org.easy4j.plugin.security.exception.AuthzException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect(Controller.class)
public class AuthzAnnotationAspect extends AbstractProxy {

    private static final Class[] ANNOTATION_CLASS_ARRAY = {
            User.class
    };

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        Annotation annotation = getAnnotation(cls, method);
        if(annotation != null){
            Class<?> annotationType = annotation.annotationType();
            if(annotationType.equals(User.class)){
                handleUser();
            }
        }
    }

    /**
     * 获取注解类
     * @param cls
     * @param method
     * @return
     */
    private Annotation getAnnotation(Class<?> cls, Method method){
        for(Class<? extends Annotation> annotationClass : ANNOTATION_CLASS_ARRAY){
            if(method.isAnnotationPresent(annotationClass)){
                return method.getAnnotation(annotationClass);
            }

            if(cls.isAnnotationPresent(annotationClass)){
                return cls.getAnnotation(annotationClass);
            }
        }

        return null;
    }

    private void handleUser(){
        Subject currentUser = SecurityUtils.getSubject();

        if(currentUser == null){
            throw new AuthzException("用户尚未登录");
        }

        PrincipalCollection principalCollection = currentUser.getPreviousPrincipals();
    }
}

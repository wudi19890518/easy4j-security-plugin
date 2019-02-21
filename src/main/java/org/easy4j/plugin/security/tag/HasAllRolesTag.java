package org.easy4j.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

import java.util.Arrays;

/**
 *
 */
public class HasAllRolesTag extends RoleTag {
    protected boolean showTagBody(String roleNames) {
        Subject subject = getSubject();
        if(subject != null){
            if(subject.hasAllRoles(Arrays.asList(roleNames.split(",")))){
                return true;
            }
        }
        return false;
    }
}

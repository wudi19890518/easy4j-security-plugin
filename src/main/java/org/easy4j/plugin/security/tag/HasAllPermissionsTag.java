package org.easy4j.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 *
 */
public class HasAllPermissionsTag extends PermissionTag {


    protected boolean showTagBody(String permissionNames) {
        Subject subject = getSubject();
        if(subject != null){
            if(subject.isPermittedAll(permissionNames.split(","))){
                return true;
            }
        }
        return false;
    }
}

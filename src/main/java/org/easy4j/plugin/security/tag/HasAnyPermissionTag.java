package org.easy4j.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 *
 */
public class HasAnyPermissionTag extends PermissionTag {
    protected boolean showTagBody(String permissionNames) {

        Subject subject = getSubject();
        if(subject != null){
           for(String perm : permissionNames.split(",")){
                if(subject.isPermitted(perm)){
                    return true;
                }
           }
        }

        return false;
    }
}

package cn.yunhe.realm;

import cn.yunhe.pojo.CurrentUser;
import cn.yunhe.pojo.ModuleP;
import cn.yunhe.pojo.UserP;
import cn.yunhe.service.UserPService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserPService userPService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        UserP userP = userPService.loginP(username);
        if(userP==null){
            return null;
        }

        String password = userP.getPassword();
        String salt = userP.getUserName()+userP.getUserId();
        CurrentUser currentUser = userPService.findUserInfoDeptByUser(userP);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser,password, ByteSource.Util.bytes(salt),this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        CurrentUser currentUser = (CurrentUser)principalCollection.getPrimaryPrincipal();
        UserP userP = currentUser.getUserP();
        List<ModuleP> permissions = userPService.getPermissionsByUserId(userP.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(permissions!=null&&permissions.size()>0){
            for(ModuleP moduleP:permissions){
                info.addStringPermission(moduleP.getCpermission());
            }
        }

        return info;
    }



    public void clearCached(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}

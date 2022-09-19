package cn.yunhe.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class HashRealm extends AuthorizingRealm {
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token令牌中获取身份信息: 输入的用户名信息
        String username=(String)authenticationToken.getPrincipal();
        if(!username.equals("zhangsan")){
            return null;
        }
        //根据用户名查询数据库,获取数据库中保存的用户信息(用户名，密码，盐, 散列次数)
        String password="05cd999b07d5683b06ffcba475411ece"; // 123+salt("yunhe")
        String salt="yunhe";
        //SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(salt),this.getName());

        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}

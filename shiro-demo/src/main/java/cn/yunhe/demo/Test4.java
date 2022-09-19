package cn.yunhe.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * 授权
 */
public class Test4 {
    public static void main(String[] args) {
        //1.构建SecurityManager工厂，IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //2.通过工厂创建SecurityManager
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //3.使用SecurityUtils将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.创建一个Subject实例，该实例认证要使用上边创建的securityManager进行
        Subject subject = SecurityUtils.getSubject();
        //5.创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        //6.用户登录(认证)
        subject.login(token);
        //查看用户认证状态
        boolean authenticated = subject.isAuthenticated();
        System.out.println("用户认证的状态:"+authenticated);
        //7.授权
        //判断是否拥有指定的角色
        boolean role1 = subject.hasRole("role3");
        System.out.println("是否拥有role1角色:"+role1);
        boolean role12 = subject.hasAllRoles(Arrays.asList("role1","role2"));
        System.out.println("是否拥有role1和role2角色:"+role12);
        //判断是否拥有指定的权限
        boolean permitted1 = subject.isPermitted("user:create");
        System.out.println("是否拥有创建权限:"+permitted1);
        boolean[] permittedArr = subject.isPermitted("user:create", "user:update","user:delete");
        System.out.println("是否拥有创建和修改的权限:"+Arrays.toString(permittedArr));
        boolean permittedAll = subject.isPermittedAll("user:create", "user:update");
        System.out.println("是否拥有指定的所有权限:"+permittedAll);

    }
}

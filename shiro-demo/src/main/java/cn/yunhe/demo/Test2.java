package cn.yunhe.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Test2 {
    public static void main(String[] args) {
        //1.构建SecurityManager工厂，IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.通过工厂创建SecurityManager
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //3.使用SecurityUtils将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.创建一个Subject实例，该实例认证要使用上边创建的securityManager进行
        Subject subject = SecurityUtils.getSubject();
        //5.创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        //6.用户登录
        subject.login(token);
        //查看用户认证状态
        boolean authenticated = subject.isAuthenticated();
        System.out.println("用户认证的状态:"+authenticated);
        //退出登录
        subject.logout();
        //查看用户认证状态
        boolean authenticated2 = subject.isAuthenticated();
        System.out.println("用户认证的状态:"+authenticated2);
    }
}

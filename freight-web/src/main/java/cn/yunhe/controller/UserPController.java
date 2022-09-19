package cn.yunhe.controller;

import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.PageBean;
import cn.yunhe.pojo.UserP;
import cn.yunhe.pojo.UserinfoP;
import cn.yunhe.service.*;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPController {
    @Autowired
    private UserPService userPService;
    @Autowired
    private UserinfoPService userinfoPService;
    @Autowired
    private DeptPService deptPService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String shiroLoginFailure = (String)request.getAttribute("shiroLoginFailure");
        System.out.println("---------->"+shiroLoginFailure);
        if(UnknownAccountException.class.getName().equals(shiroLoginFailure)){
            request.setAttribute("errorInfo","账号不正确!");
        }
        if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
            request.setAttribute("errorInfo"," 密码不正确!");
        }
        return "index";
    }
    /**
     * 分页查询用户信息
     * @param pageBean
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String listUserOfPage(PageBean pageBean, Model model) throws Exception {
        PageBean pb = userPService.listUserOfPage(pageBean);
        model.addAttribute("pb",pb);
        model.addAttribute("url","/user/list");
        return "sysadmin/user/jUserList";
    }

    @RequestMapping("tocreate")
    public String tocreate(Model model, UserP userP) throws Exception {
        //查询所有部门
        List<DeptP> deptPList = deptPService.listDept();
        model.addAttribute("ds",deptPList);
        //查询所有用户扩展信息
        List<UserinfoP> userInfoPList = userinfoPService.listUserInfo();
        model.addAttribute("uis",userInfoPList);
        return "sysadmin/user/jUserCreate";
    }

    @RequestMapping("/create")
    public String createUser(UserP userP,UserinfoP userinfoP)throws Exception {
        userPService.createUser(userP,userinfoP);
        return "redirect:/user/list";
    }

}

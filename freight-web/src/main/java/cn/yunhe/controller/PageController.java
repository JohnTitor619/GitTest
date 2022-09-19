package cn.yunhe.controller;

import cn.yunhe.pojo.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping("/index")
    public String showLogin(){
        return "index";
    }


    @RequestMapping("/main")
    public String main(){
        return "home/fmain";
    }



    @RequestMapping("/homeAction_title")
    public String homeAction_title(Model model){
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser)subject.getPrincipal();
        model.addAttribute("currentUser",currentUser);
        return "home/title";
    }

    @RequestMapping("/homeAction_toleft")
    public String homeAction_toleft(String moduleName){
        return moduleName+"/left";
    }


    @RequestMapping("/homeAction_tomain")
    public String homeAction_tomain(String moduleName){
        return moduleName+"/main";
    }


}

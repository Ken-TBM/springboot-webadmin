package com.kenproject.admin.controller;

import com.kenproject.admin.bean.Account;
import com.kenproject.admin.bean.User;
import com.kenproject.admin.service.AccountService;
import com.kenproject.admin.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    AccountServiceImpl accountService;

    @ResponseBody
    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Integer id){
        return accountService.getAcctById(id);
    }

    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if (StringUtils.hasLength(user.getUserName())&&"123456".equals(user.getPassword())){
            //把登录成功的用户保存起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到main页面，重定向防止表单重复提交
            return "redirect:/main.html";
        }
        model.addAttribute("msg","账号密码错误");
        //回到登录页面
        return "login";
    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        /*//是否登录。拦截器，过滤器
        Object user = session.getAttribute("loginUser");
        if (user!=null){
            return "main";
        }
        //返回登录页面
        model.addAttribute("msg","请重新登录");
        return "login";*/
        return "main";
    }
}

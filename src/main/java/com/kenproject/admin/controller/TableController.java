package com.kenproject.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kenproject.admin.bean.User;
import com.kenproject.admin.exception.UserToManyException;
import com.kenproject.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@SuppressWarnings({"all"})
public class TableController {
    @Autowired
    UserService userService;


    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id")Long id, @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){

        userService.removeById(id);
        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }


    /**
     *  不带请求参数或者参数类型匹配不对 400：Bad Request 一般都是浏览器的参数没有传递正确
     * @param a
     * @return
     */
    @GetMapping("/basic_table")
    public String basic_table(@RequestParam("a") int a){
       /* int i=10/0;*/
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //表格内容遍历
//        List<User> users = Arrays.asList(new User("ken", "ken"),
//                new User("coco", "coco"),
//                new User("Allen", "Allen"),
//                new User("lucy", "lucy"));
//        model.addAttribute("users",users);
//        if (users.size()>3){
//            throw new UserToManyException();
//        }

        List<User> list = userService.list();
        model.addAttribute("users",list);
        //分页查询数据
        Page<User> userPage = new Page<>(pn, 2);//当前页码，显示几条记录
        //返回分页查询的结果
        Page<User> page = userService.page(userPage, null);
        long current = page.getCurrent();
        long pages = page.getPages();
        long total = page.getTotal();
        List<User> records = page.getRecords();
        model.addAttribute("page",page);

        return "table/dynamic_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }
}

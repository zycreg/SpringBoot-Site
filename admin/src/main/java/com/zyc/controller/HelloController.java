package com.zyc.controller;

import com.zyc.entity.Menu;
import com.zyc.entity.User;
import com.zyc.service.IMenuService;
import com.zyc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhangyuancheng on 2017/10/31.
         */
@Controller
public class HelloController {
    @Autowired
    private IUserService userService;


    @Autowired
    private IMenuService menuService;

    @RequestMapping("/hello")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World1") String name, Model model) {
        //User user = userService.getUserInfo();
        model.addAttribute("name", name);
        //得到一级菜单
        List<Menu> menuList = menuService.getRootMenu();
        model.addAttribute("menus",menuList);
        return "hello";
    }
}

package com.zyc.controller;
import com.zyc.entity.Menu;
import com.zyc.exception.UserNotFoundException;
import com.zyc.global.Constants;
import com.zyc.entity.User;
import com.zyc.service.IMenuService;
import com.zyc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author zhangyuancheng
 * @date 2017-10-12
 * 管理员登录、注销
 */

@Controller
public class LoginController {


    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    /***
     * 跳转到登录页面
     * @param session
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpSession session){
        if(session.getAttribute(Constants.CONSOLE_ADMIN_USER)!=null){
            return "redirect:./index";
        }
        return "login";
    }

    /**
     * 执行登录动作
     * @param session
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/login_do",method = RequestMethod.POST)
    public String login_do(HttpSession session, User user, Model model){
        try {
            User loginUser = userService.login(user);
            session.setAttribute(Constants.CONSOLE_ADMIN_USER,loginUser);
            return "redirect:./index";
            //return new ResponseData("10000","登录成功",loginUser);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("loginStatus","fail");
            return "login";
        }
    }

    /***
     * 跳转到控制台首页
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpSession httpSession, @RequestParam(value = "pMenuId",defaultValue = "2") Integer pMenuId){
        //得到一级菜单
        List<Menu> menuList = menuService.getRootMenu();
        httpSession.setAttribute("menus",menuList);

        //List<Menu> leftMenus = menuService.getAllLeftMenus();
        List<Menu> leftMenus = menuService.getMenusByPid(pMenuId);
        httpSession.setAttribute("leftMenus",leftMenus);
        return "index";
    }

    /***
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //session.removeAttribute(Constants.CONSOLE_ADMIN_USER);
        session.invalidate();
        return "login";
    }

}

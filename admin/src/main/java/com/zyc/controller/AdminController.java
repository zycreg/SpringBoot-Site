package com.zyc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyc.entity.User;
import com.zyc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述：
 * 用户管理
 *
 * @author zhangyuancheng
 * @create 2017-11-02 下午3:28
 */
@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private IUserService userService;

    /**
     * 用户列表
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("list")
    public String list(HttpServletRequest request,User user, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize, Model model) {
        String keyword = request.getParameter("keyword");
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        user.setUsername(keyword);
        List<User> users = userService.queryUserList(user);
        PageInfo pageInfo = new PageInfo(users,5);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/list";
    }

    @RequestMapping("listJSON")
    @ResponseBody
    public PageInfo listJSON(User user, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize, Model model) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<User> users = userService.queryUserList(user);
        PageInfo pageInfo = new PageInfo(users,5);
        return pageInfo;
    }

    /***
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "add")
    public String add() {
        return "admin/add";
    }

    /***
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "insert")
    public String insert(User user) {
        userService.insertUser(user);
        return "redirect:./list";
    }

    /**
     * 跳转到修改用户界面
     *
     * @return
     */
    @RequestMapping(value = "modify")
    public String modify() {
        return "admin/modify";
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "update")
    public String update(User user) {
        userService.updateUser(user);
        return "redirect:./list";
    }

}

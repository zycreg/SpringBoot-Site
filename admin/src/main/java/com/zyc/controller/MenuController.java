package com.zyc.controller;

import com.zyc.entity.Menu;
import com.zyc.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangyuancheng
 * @create 2017-11-05 下午4:28
 * @descripition 菜单管理控制器
 */
@Controller
@RequestMapping("/menu/")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 获取左侧菜单
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "getSubMensByPid")
    @ResponseBody
    public List<Menu> getSubMensByPid(@RequestParam(value = "pid", required = true, defaultValue = "2") Integer pid) {
        return menuService.getMenusByPid(pid);
    }


    @RequestMapping(value = "getAllLeftMenus")
    @ResponseBody
    public List<Menu> getAllLeftMenus() {
        return menuService.getAllLeftMenus();
    }

}

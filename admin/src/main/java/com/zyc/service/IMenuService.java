package com.zyc.service;

import com.zyc.entity.Menu;

import java.util.List;

public interface IMenuService {
    /***
     * 得到一级菜单
     * @return
     */
    public List<Menu> getRootMenu();



    /***
     * 加载左侧菜单
     * @return
     */
    public List<Menu> getMenusByPid(Integer pid);

    /***
     * 得到所有二级菜单树
     * @return
     */
    public List<Menu> getAllLeftMenus();

}

package com.zyc.mapper;

import com.zyc.entity.Menu;

import java.util.List;

/**
 * @author zhangyuancheng
 * @create 2017-11-05 下午4:08
 * @descripition 菜单
 */
public interface MenuMapper {
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

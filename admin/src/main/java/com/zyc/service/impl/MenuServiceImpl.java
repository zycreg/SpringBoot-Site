package com.zyc.service.impl;

import com.zyc.entity.Menu;
import com.zyc.mapper.MenuMapper;
import com.zyc.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyuancheng
 * @create 2017-11-05 下午4:27
 * @descripition 菜单管理
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getRootMenu() {
        return menuMapper.getRootMenu();
    }


    @Override
    public List<Menu> getMenusByPid(Integer pid) {
       return menuMapper.getMenusByPid(pid);
    }

    @Override
    public List<Menu> getAllLeftMenus() {
        return menuMapper.getAllLeftMenus();
    }


}

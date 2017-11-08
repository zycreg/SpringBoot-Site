package com.zyc.service;

import com.zyc.exception.UserNotFoundException;
import com.zyc.entity.User;

import java.util.List;

/**
 * Created by zhangyuancheng on 2017/9/12.
 */

public interface IUserService {

    /**
     * 查询用户信息
     * @return
     */
    public User getUserInfo() ;

    /***
     * 用户登录
     * @return
     */
    public User login(User user) throws UserNotFoundException;


    /***
     * 查询用户分页列表
     * @param user
     * @return
     */
    public List queryUserList(User user);

    /***
     * 添加新用户
     * @param user
     */
    public void insertUser(User user);

    /***
     * 修改用户
     * @param user
     */
    public void updateUser(User user);

    /***
     * 删除用户
     */
    public void deleteUser(String id);


    /***
     * 删除多个用户
     */
    public void deleteUser(String[] ids);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    public User findById(Integer id);

}

package com.zyc.mapper;

import com.zyc.entity.User;

import java.util.List;

/**
 * Created by zhangyuancheng on 2017/9/12.
 */
public interface UserMapper {

    /***
     * 查询用户信息
     * @return
     */
    public User findUserInfo();

    /***
     * 根据用户名和密码得到用户信息
     * @return
     */
    public User getUserByUsernameAndPassword(User user);

    /**
     * 根据用户名取用户编号是否可用
     * @param id
     * @return
     */
    public Integer getUserIsVisibleById(Integer id);

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
     * @param ids
     */
    public void deleteUser(String ids);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    public User findById(Integer id);

}

package com.zyc.service.impl;

import com.zyc.exception.UserNotFoundException;
import com.zyc.mapper.UserMapper;
import com.zyc.entity.User;
import com.zyc.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyuancheng on 2017/9/12.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User login(User user) throws UserNotFoundException {
        User loginUser = userMapper.getUserByUsernameAndPassword(user);
        if (loginUser == null) {
            throw new UserNotFoundException("用户不存在！");
        } else {
            return loginUser;
        }
    }

    @Override
    public List queryUserList(User user) {
        return userMapper.queryUserList(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String ids) {
        userMapper.deleteUser(ids);
    }

    @Override
    public void deleteUser(String[] ids) {
        for(String id : ids){
            userMapper.deleteUser(id);
        }
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}

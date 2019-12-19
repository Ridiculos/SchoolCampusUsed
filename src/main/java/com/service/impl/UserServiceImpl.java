package com.service.impl;

import com.dao.UserDao;
import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        User user = userDao.findOneByUsernameAndPassword(username, password);
        if(user != null)
            return user;
        else
            return null;
    }

    @Override
    public int register(User user) {
        synchronized (this){
            while (true){
                String userId = UUID.randomUUID().toString();
                //判断是否存在 ID 重复
                if(userDao.findOneById(userId) == null){
                    user.setUserId(userId);
                    //判断是否存在用户名重复
                    User one = userDao.findOneByUsername(user.getUsername());
                    if(one == null){
                        int count = userDao.createUser(user);
                        if(count == 1){
                            return 1;//返回1表示创建成功
                        }else{
                            return -1;//表示未知错误
                        }
                    }else{
                        return 0;//表示用户名重复
                    }
                }
            }
        }
    }

    @Override
    public User getInfo(String userId) {
        User user = userDao.findOneById(userId);
        return user;
    }

    @Override
    public int setInfo(User user) {
        int count = userDao.changeUserById(user.getUserId(), user.getName(), user.getPassword(), user.getSex());
        return count;
    }
}

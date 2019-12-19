package com.service;

import com.domain.User;

public interface UserService {
    /**
     * 登录
     */
    User login(String username,String password);

    /**
     * 注册
     */
    int register(User user);

    /**
     * 返回个人信息
     * @param userId
     * @return
     */
    User getInfo(String userId);

    /**
     * 保存个人信息
     */
    int setInfo(User user);
}

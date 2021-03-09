package com.zzxx.travel.dao;

import com.zzxx.travel.domain.User;


public interface UserDao {
    User findUserByName(String username);


    void saveUser(User user);


    int active(String code);

    //账号密码查询用户。返回user
    User selectUserByUsnamePass(String username,String password);


}

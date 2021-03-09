package com.zzxx.travel.service;

import com.zzxx.travel.domain.Category;
import com.zzxx.travel.domain.User;
import com.zzxx.travel.util.LoginException;

import java.util.List;

public interface UserService {
    boolean checkUser(String username);

    Boolean registUser(User user);


    Boolean active(String code);

    //login方法（username,password）返回user
    User login(String username, String password) throws LoginException, LoginException;



}

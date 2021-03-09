package com.zzxx.travel.service.impl;

import com.zzxx.travel.dao.UserDao;
import com.zzxx.travel.dao.impl.UserDaoImpl;
import com.zzxx.travel.domain.Category;
import com.zzxx.travel.domain.User;
import com.zzxx.travel.service.UserService;
import com.zzxx.travel.util.LoginException;
import com.zzxx.travel.util.MailUtils;
import com.zzxx.travel.util.UuidUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao us = new UserDaoImpl();

    @Override
    public boolean checkUser(String username) {

        try{
          us.findUserByName(username);
          return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean registUser(User user) {
        try {
            //设置账号未激活
            user.setStatus("N");
            //设置唯一code
            user.setCode(UuidUtil.getUuid());
            //保存user的信息
            us.saveUser(user);
            //发送激活邮件
            String text="<a href='http://localhost:80/travel/UserMethod/active?code="+user.getCode()+"'>账号激活</a>";
            MailUtils.sendMail(user.getEmail(),text,"zzxx注册");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean active(String code) {
        int count =us.active(code);
        return count !=0;
    }

    @Override
    public User login(String username, String password) throws LoginException {
        //调用selectUserByUsnamePass判断登陆的账号密码
            User user = us.selectUserByUsnamePass(username, password);
            if(user==null||user.getStatus().equals("N")){
                throw new LoginException("账号不存在或者未激活");
            }else {
                return user;
            }
    }



    //login 。user==null 账号密码错 user.getStaus为N，账号好未激活
}

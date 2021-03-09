package com.zzxx.travel.dao.impl;

import com.zzxx.travel.dao.UserDao;
import com.zzxx.travel.domain.User;
import com.zzxx.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByName(String username) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql ="select * from tab_user where username = ? ;";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        return user;


    }

    @Override
    public void saveUser(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into tab_user value(null,?,?,?,?,?,?,?,?,?)";
         jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(),
                user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }

    @Override
    public int active(String code) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update tab_user set status = 'Y' where code = ?";
        int count = jdbcTemplate.update(sql, code);
        return count;
    }

    @Override
    public User selectUserByUsnamePass(String username, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from tab_user where username = ? and password= ? ;";
        try{
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        }catch (Exception e){
            return  null;
        }
    }


}

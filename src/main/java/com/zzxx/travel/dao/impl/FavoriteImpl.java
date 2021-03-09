package com.zzxx.travel.dao.impl;

import com.zzxx.travel.dao.FavoriteDao;
import com.zzxx.travel.domain.Favorite;
import com.zzxx.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FavoriteImpl implements FavoriteDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findCountByrid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, rid);
        return integer;
    }

    @Override
    public Boolean findIfFavoByUidAndRid(int uid, int rid) {
        String sql = "select * from tab_favorite where uid = ? and rid = ?";
        List<Favorite> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, rid);
        if(query.size()!=0 && query.get(0) !=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void insertFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite(rid,date,uid) values (?,?,?) ;";

        int update = jdbcTemplate.update(sql, rid, new Date(), uid);
    }
}

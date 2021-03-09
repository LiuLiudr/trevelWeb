package com.zzxx.travel.dao.impl;

import com.zzxx.travel.dao.RouteDao;
import com.zzxx.travel.domain.Route;
import com.zzxx.travel.domain.RouteImg;
import com.zzxx.travel.domain.Seller;
import com.zzxx.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.rowset.serial.SerialStruct;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Route> findRouteByPage(int startRow, int pagesize, int cid) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";
        List<Route> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, startRow, pagesize);

        System.out.println("three"+query.get(0));

        return query.size()==0 ? null :query ;
    }

    @Override
    public int findTotalcount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, cid);
        return integer;
    }

    //查询route
    @Override
    public Route findRouteByRid(int rid) {
        String sql ="select * from tab_route where rid = ?";
        try{
            Route route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
            return route;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Seller findSellerBySid(int sid) {
        String sql = "select * from tab_seller where sid = ? ";
        try{
            Seller seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
            return seller;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RouteImg> findRouteImgByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ? ";
        List<RouteImg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return query.size()==0? null : query;
    }
}

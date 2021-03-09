package com.zzxx.travel.dao.impl;

import com.zzxx.travel.dao.CategoryDao;
import com.zzxx.travel.domain.Category;
import com.zzxx.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;



public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> FindAllCategory() {
       String sql = "select * from tab_category ;";
        List<Category> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return query.size() == 0? null :query;
    }
}

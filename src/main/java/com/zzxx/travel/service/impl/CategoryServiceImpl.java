package com.zzxx.travel.service.impl;

import com.zzxx.travel.dao.CategoryDao;
import com.zzxx.travel.dao.impl.CategoryDaoImpl;
import com.zzxx.travel.domain.Category;
import com.zzxx.travel.service.CategoryService;
import com.zzxx.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> FindallCategory() {
        //1.先从redis缓存查
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        if(category==null || category.size()==0){
            //没查到从数据库查询并返回
            List<Category> categoryList = categoryDao.FindAllCategory();
            //并且从数据库中查到的要存到redis缓存中
            for(Category cate:categoryList){
                jedis.zadd("category",cate.getCid(),cate.getCname());
            }
            System.out.println("从数据库查到");
            return categoryList;
        }else {
            //直接在redise查到了，直接封装
            System.out.println("从redise查到");
            List<Category> categoryList = new ArrayList<>();
            for(Tuple tuple :category){
                Category category1= new Category((int)tuple.getScore(),tuple.getElement());
                categoryList.add(category1);
            }
            return categoryList;
            }
        }
}

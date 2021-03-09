package com.zzxx.travel.service.impl;

import com.zzxx.travel.dao.FavoriteDao;
import com.zzxx.travel.dao.RouteDao;
import com.zzxx.travel.dao.impl.FavoriteImpl;
import com.zzxx.travel.dao.impl.RouteDaoImpl;
import com.zzxx.travel.domain.PageBean;
import com.zzxx.travel.domain.Route;
import com.zzxx.travel.domain.RouteImg;
import com.zzxx.travel.domain.Seller;
import com.zzxx.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    FavoriteDao favoriteDao = new FavoriteImpl();
    @Override
    public PageBean<Route> findRouteBypage(int _currentpage, int _pagesize, int _cid) {

        int startRow = (_currentpage-1)*_pagesize;
        //按当前页计算查询list
        List<Route> list = routeDao.findRouteByPage(startRow,_pagesize,_cid);
        //查询记录总数
        int _totalcount = routeDao.findTotalcount(_cid);
        //计算总页数
        int _totaopage = (_totalcount+_pagesize-1)/_pagesize;
        //封装成pagebean
        PageBean<Route> pageBean = new PageBean<>(list,_totalcount,_totaopage,_currentpage,_pagesize);
        return pageBean;
    }

    @Override
    public Route findOneRoute(int rid) {
        //调用RouteDao，根据rid查询Route
        Route route = routeDao.findRouteByRid(rid);
        //sid查selle对象
        Seller seller = routeDao.findSellerBySid(route.getSid());
        //rid查routeImg对象
        List<RouteImg> routeImg = routeDao.findRouteImgByRid(rid);
        //调用favoriteDao根据rid查询本线路收藏次数
        int count = favoriteDao.findCountByrid(rid);
        //属性注入
        route.setSeller(seller);
        route.setRouteImgList(routeImg);
        route.setCount(count);
        //返回route
        return route;
    }
}

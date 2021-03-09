package com.zzxx.travel.dao;

import com.zzxx.travel.domain.Route;
import com.zzxx.travel.domain.RouteImg;
import com.zzxx.travel.domain.Seller;

import java.util.List;

public interface RouteDao {

    int findTotalcount(int cid);

    List<Route> findRouteByPage(int startRow, int pagesize, int cid);

    Route findRouteByRid(int rid);
    Seller findSellerBySid(int sid);
    List<RouteImg> findRouteImgByRid(int rid);
}

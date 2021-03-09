package com.zzxx.travel.service;

import com.zzxx.travel.domain.PageBean;
import com.zzxx.travel.domain.Route;

public interface RouteService {
    PageBean<Route> findRouteBypage(int currentpage, int pagesize, int cid);

    Route findOneRoute(int rid);
}

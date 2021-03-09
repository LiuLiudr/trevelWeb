package com.zzxx.travel.service.impl;

import com.zzxx.travel.dao.FavoriteDao;
import com.zzxx.travel.dao.impl.FavoriteImpl;
import com.zzxx.travel.domain.Favorite;
import com.zzxx.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteImpl();

    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.insertFavorite(rid, uid);
    }

    @Override
    public int findCountByRid(int rid) {
        int countByrid = favoriteDao.findCountByrid(rid);
        return countByrid;
    }

    @Override
    public Boolean findByUidAndRid(int uid, int rid) {
        Boolean bo = favoriteDao.findIfFavoByUidAndRid(uid, rid);
        if(bo){
            return true;
        }else {
            return false;
        }
    }
}

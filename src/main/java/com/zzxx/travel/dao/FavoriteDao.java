package com.zzxx.travel.dao;

import com.zzxx.travel.domain.Favorite;

public interface FavoriteDao {
    int findCountByrid(int rid);

    Boolean findIfFavoByUidAndRid(int uid, int rid);

    void insertFavorite(int rid,int uid);
}

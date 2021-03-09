package com.zzxx.travel.service;

import com.zzxx.travel.domain.Favorite;

public interface FavoriteService {
    void addFavorite(int rid , int uid);

    int findCountByRid(int rid);

    Boolean findByUidAndRid(int uid , int rid);

}

package com.example.myapplication.mvvm.model;


import com.example.myapplication.mvvm.model.result.QueryMergeResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaiduMusicApi {
    String HOST = "http://tingapi.ting.baidu.com/";
    String V1_TING = "v1/restserver/ting";
    String QUERY_MERGE = "baidu.ting.search.merge";
    String SEARCH_CATALOGSUG = "baidu.ting.search.catalogSug";
    String SONG_LRC = "baidu.ting.song.lry";
    String GET_ARTISTSONGLIST = "baidu.ting.artist.getSongList";
    String SONG_PLAY = "baidu.ting.song.play";
    String GET_ARTISTINFO = "baidu.ting.artist.getinfo";
    int pagenSize = 20;
    String DownloadUrl = "http://ting.baidu.com/data/music/links?songIds=";

    @GET(V1_TING + "?method=" + QUERY_MERGE)
    Observable<QueryMergeResp> queryMerge(@Query("query") String query,
                                          @Query("page_no") int pageNo,
                                          @Query("page_size") int pageSize);
}

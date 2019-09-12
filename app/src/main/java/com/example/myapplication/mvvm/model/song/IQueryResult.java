package com.example.myapplication.mvvm.model.song;

public interface IQueryResult {
    int NONE = 0;
    int SONG = 1;
    int ALBUM = 2;
    int ARTIST = 3;

    String getName();

    /**
     * 获取类型
     */
    int getSearchResultType();
}

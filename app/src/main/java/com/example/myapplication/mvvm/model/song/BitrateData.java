package com.example.myapplication.mvvm.model.song;

import java.util.List;

public class BitrateData {
    public String xcode;
    public List<BSong> songList;


    public List<BSong> getSongList() {
        return songList;
    }

    public void setSongList(List<BSong> songList) {
        this.songList = songList;
    }
}

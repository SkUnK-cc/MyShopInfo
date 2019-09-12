package com.example.myapplication.mvvm.model.result;

import com.example.myapplication.mvvm.model.song.Song;

import java.util.List;

public class Song_info {
    public int total;
    public List<Song> song_list;

    public int getTotal() {
        return total;
    }

    public List<Song> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<Song> song_list) {
        this.song_list = song_list;
    }
}

package com.example.myapplication.mvvm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.mvvm.model.result.QueryMergeResp

class ViewModelMusicFragment: ViewModel() {
    var searchWord = MutableLiveData<String>()
    var failedVisibility = MutableLiveData<Int>()
    var list = MutableLiveData<QueryMergeResp>()
    private var repository:MusicRepository = MusicRepository()

    init{
        searchWord.value = "稻香"
        failedVisibility.value = View.INVISIBLE
    }

    fun getSongList(text: String) {
        repository.getSongListFromNet(text,list)
    }
}
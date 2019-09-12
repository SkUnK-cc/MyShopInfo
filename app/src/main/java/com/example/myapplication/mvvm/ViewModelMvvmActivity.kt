package com.example.myapplication.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMvvmActivity : ViewModel(){
    var title = MutableLiveData<String>()

    init {
        title.value = "标题"
    }
}
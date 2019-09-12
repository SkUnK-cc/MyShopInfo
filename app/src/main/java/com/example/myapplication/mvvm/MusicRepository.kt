package com.example.myapplication.mvvm

import android.util.Log
import android.util.LruCache
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.mvvm.model.RetrofitFactory
import com.example.myapplication.mvvm.model.result.QueryMergeResp
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MusicRepository {
    private val mergeCache = LruCache<String,QueryMergeResp>(10)

    fun getSongListFromNet(word: String, list: MutableLiveData<QueryMergeResp>){
        var cache = mergeCache.get(word)
        if(cache!=null){
            list.value = cache
            return
        }

        RetrofitFactory.provideBaiduApi()
            .queryMerge(word,1,50)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object:Observer<QueryMergeResp>{
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: QueryMergeResp) {
                    if(t!=null && t.isValid){
                        list.value = t
                        mergeCache.put(word,t)
                    }
                }
                override fun onError(e: Throwable) {
                    Log.e("throwable", e.message)
                }
            })
    }
}
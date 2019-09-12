package com.example.myapplication.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMvvmBinding
class MvvmActivity : AppCompatActivity() {

    var binding:ActivityMvvmBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm)
        binding?.viewModel = ViewModelProviders.of(this).get(ViewModelMvvmActivity::class.java)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.frame,MusicFragment()).commit()
    }
}

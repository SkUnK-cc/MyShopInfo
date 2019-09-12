package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.mvvm.MvvmActivity
import com.example.myapplication.nestedscroll.NestedScrollActivity
import com.example.myapplication.shopinfo.ShopInfoActivity
import kotlinx.android.synthetic.main.activity_jump.*

class JumpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jump)
        initView()
    }

    private fun initView() {

    }

    fun onButtonClick(view: View) {
        var intent:Intent? = null
        intent = when(view.id){
            R.id.main->{
                Intent(this,MainActivity::class.java)
            }
            R.id.ShopInfo->{
                Intent(this,ShopInfoActivity::class.java)
            }
            R.id.NestedScroll->{
                Intent(this,NestedScrollActivity::class.java)
            }
            R.id.MvvmDemo->{
                Intent(this,MvvmActivity::class.java)
            }
            else->{
                Intent(this,MainActivity::class.java)
            }
        }
        startActivity(intent)
    }
}

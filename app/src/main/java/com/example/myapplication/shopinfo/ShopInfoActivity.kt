package com.example.myapplication.shopinfo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.example.myapplication.shopinfo.fragment.active.FragmentActive
import com.example.myapplication.shopinfo.fragment.program.FragmentProgram
import kotlinx.android.synthetic.main.activity_shop_info.*

class ShopInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_info)
        setStatusBarFullTransparent()
//        setFitSystemWindow(true)
        initView()
    }

    @SuppressLint("ResourceType")
    private fun initView() {
        shopinfoToolbar.overflowIcon = resources.getDrawable(R.drawable.ic_more)
        shopinfoToolbar.inflateMenu(R.menu.shopinfo_tool_menu)
        shopinfoToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.toolbar_share ->{
                    Toast.makeText(this,"share",Toast.LENGTH_SHORT).show()
                    true
                }
                else->{
                    Toast.makeText(this,"more",Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

        var tabTitle = arrayOf("活动","卡卷","节目","游戏","店铺")
        var i = 0
        tabTitle.forEach {
            var tab = tablayout.newTab()
            tab.tag = i++
            tab.text = it
            tablayout.addTab(tab)
        }

        var fragments = arrayListOf<Fragment>()
        fragments.add(FragmentActive())
        fragments.add(FragmentActive())
        fragments.add(FragmentProgram())
        fragments.add(FragmentActive())
        fragments.add(FragmentActive())
        viewpager.adapter = object: FragmentStatePagerAdapter(supportFragmentManager){
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tabTitle[position]
            }
        }
        viewpager.offscreenPageLimit = fragments.size
        tablayout.setupWithViewPager(viewpager)
    }

    private fun setStatusBarFullTransparent(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }else if(Build.VERSION.SDK_INT >= 19){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun setFitSystemWindow(fisSystemWindow: Boolean){
        var content = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        content.fitsSystemWindows = fisSystemWindow
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shopinfo_tool_menu,menu)
        return true
    }
}

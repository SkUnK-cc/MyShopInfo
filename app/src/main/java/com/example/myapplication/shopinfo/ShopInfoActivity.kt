package com.example.myapplication.shopinfo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import com.example.myapplication.shopinfo.fragment.active.FragmentActive
import com.example.myapplication.shopinfo.fragment.program.FragmentProgram
import com.example.myapplication.utils.DimensionUtil
import com.example.myapplication.utils.LogUtils
import kotlinx.android.synthetic.main.activity_shop_info.*

class ShopInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_info)
        setStatusBarFullTransparent()
//        setFitSystemWindow(true)
        initView()
    }

    @Suppress("DEPRECATION")
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

        var tags = resources.getStringArray(R.array.tags)
        var lp = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.setMargins(5)
        for(s in tags){
            var textView = TextView(this)
            textView.setPadding(DimensionUtil.dp2px(this,3F).toInt())
            textView.text = s
            textView.textSize = 14F
            textView.setTextColor(Color.parseColor("#FFFFFF"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.background = resources.getDrawable(R.drawable.bg_circle_corner_tag)
            }
            flow_layout.addView(textView,lp)
        }

        nsView.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && nsView.viewTreeObserver.isAlive) {
                    nsView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
                var rawY = nsView.y
                var scrollViewCriticalY = shopinfoToolbar?.y!!
                scrollViewCriticalY += shopinfoToolbar?.height!!
                scrollViewCriticalY += tablayout?.height!!
                LogUtils.e("临界值: $scrollViewCriticalY") //908
                var offset = rawY-scrollViewCriticalY   //173
                LogUtils.e("offset: $offset")
                var displayHeight = windowManager.defaultDisplay.height
                var lp = nsView.layoutParams
                lp.height = (displayHeight-scrollViewCriticalY).toInt()
                nsView.layoutParams = lp
            }
        })
    }

    override fun onResume() {
        super.onResume()
        var handler = Handler(Looper.getMainLooper())
        handler.postDelayed(3000) {
            LogUtils.e("nsView.height: ${nsView.height}")
            LogUtils.e("480 to px: ${DimensionUtil.dp2px(this,480F)}")
            LogUtils.e("display.height: ${windowManager.defaultDisplay.height}")
        }

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

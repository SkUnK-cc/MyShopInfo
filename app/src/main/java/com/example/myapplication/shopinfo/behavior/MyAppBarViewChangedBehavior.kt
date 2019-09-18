package com.example.myapplication.shopinfo.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.example.myapplication.utils.LogUtils
import com.google.android.material.appbar.AppBarLayout
import java.security.AlgorithmParameterGenerator
import kotlin.math.abs

class MyAppBarViewChangedBehavior: AppBarLayout.Behavior {
    val ALPHA_VIEWGROUP = "floating_alpha_layout"
    private var alphaViewGroup : ViewGroup?=null
    val TOOLBAR = "toolbar"
    private var toolbar: Toolbar?=null
    val TABLAYOUT = "tablayout"
    private var tabLayout: ViewGroup?=null
    val NESTED_SCROLL = "nestedscrollview"
    private var nestedScrollView : NestedScrollView?=null
    val Circle_Image = "ivCircle"
    private var ivCircle :ImageView?=null
    private var scrollViewCriticalY: Float = 0F
    private var scrollRawY :Float = 0F

    constructor()
    constructor(context: Context, attrs: AttributeSet):super(context,attrs)


    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        var handled =  super.onLayoutChild(parent, abl, layoutDirection)
        alphaViewGroup = parent.findViewWithTag(ALPHA_VIEWGROUP)
        toolbar = parent.findViewWithTag(TOOLBAR)
        tabLayout = parent.findViewWithTag(TABLAYOUT)
        nestedScrollView = parent.findViewWithTag(NESTED_SCROLL)
        ivCircle = parent.findViewWithTag(Circle_Image)

        if(scrollViewCriticalY==0F) {
            scrollViewCriticalY = toolbar?.y!! + toolbar?.height!!+tabLayout?.height!!
        }

        abl.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {

                /**
                 * 此段垃圾代码不可用
                if(scrollRawY ==0F)scrollRawY=nestedScrollView!!.y
                var curY = nestedScrollView!!.y
                var alpha = (scrollRawY-curY)/(scrollRawY-scrollViewCriticalY)
                if(curY-scrollViewCriticalY<20)alpha = 1F
                if(scrollRawY-curY<20)alpha = 0F
                LogUtils.e("scrollRawY: $scrollRawY" )
                LogUtils.e("scrollViewCriticalY: $scrollViewCriticalY" )
                LogUtils.e("curY: $curY" )
                LogUtils.e("alpha: $alpha")
                alphaViewGroup?.alpha = alpha
                alphaViewGroup?.invalidate()
                ivCircle?.alpha = 1-alpha
                ivCircle?.invalidate()
                 */
                //使用 abl.totalScrollRange 方法的实时性 比 获取 nestedScrollView 的y 值 要好！！
                LogUtils.e("p1: $p1 \np0.totalScrollRange: ${p0?.totalScrollRange} \nabs(p1): ${abs(p1)}\nabs(p1).toFloat: ${abs(p1).toFloat()}\np0.totalScrollRnage.toFloat: ${p0?.totalScrollRange!!.toFloat()}")
                LogUtils.e("alpha2: ${abs(p1).toFloat()/p0?.totalScrollRange!!.toFloat()}")
                var alpha = abs(p1).toFloat()/p0?.totalScrollRange!!.toFloat()
                alphaViewGroup?.alpha = alpha
                ivCircle?.alpha = 1- alpha
            }
        })

        LogUtils.e("toolbar.height: ${toolbar?.height}")
        LogUtils.e("alphaViewGroup.height: ${alphaViewGroup?.height}")
        LogUtils.e("scrollRawY: $scrollRawY")
        LogUtils.e("scrollViewCriticalY: $scrollViewCriticalY")
        LogUtils.e("appbar getTotalScrollRange: ${abl.totalScrollRange}")   //获得abl可以滑动的最大偏移

        return handled
    }
}
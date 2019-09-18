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


//        nestedScrollView?.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
//            var curY = nestedScrollView!!.y
//            var alpha = (scrollRawY-curY)/(scrollRawY-scrollViewCriticalY)
//            LogUtils.e("alpha: $alpha")
//            LogUtils.e("curY: $curY")
//            alphaViewGroup?.alpha = alpha
//            alphaViewGroup?.invalidate()
//        }
        abl.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if(scrollRawY ==0F)scrollRawY=nestedScrollView!!.y
                var curY = nestedScrollView!!.y
                var alpha = (scrollRawY-curY)/(scrollRawY-scrollViewCriticalY)
                if(curY-scrollViewCriticalY<20)alpha = 1F
                if(scrollRawY-curY<20)alpha = 0F
                LogUtils.e("scrollRawY: $scrollRawY")
                LogUtils.e("scrollViewCriticalY: $scrollViewCriticalY")
                LogUtils.e("curY: $curY")
                LogUtils.e("alpha: $alpha")
                alphaViewGroup?.alpha = alpha
                alphaViewGroup?.invalidate()
                ivCircle?.alpha = 1-alpha
                ivCircle?.invalidate()
            }

        })

        LogUtils.e("toolbar.height: ${toolbar?.height}")
        LogUtils.e("alphaViewGroup.height: ${alphaViewGroup?.height}")
        LogUtils.e("scrollRawY: $scrollRawY")
        LogUtils.e("scrollViewCriticalY: $scrollViewCriticalY")

        return handled
    }


}
package com.example.myapplication.shopinfo.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.example.myapplication.utils.LogUtils
import com.google.android.material.appbar.AppBarLayout

class MyAppBarAlphaBehavior : AppBarLayout.Behavior{

    val ALPHA_VIEWGROUP = "floating_alpha_layout"
    private var alphaViewGroup :ViewGroup?=null
    val TOOLBAR = "toolbar"
    private var toolbar: Toolbar?=null
    val TABLAYOUT = "tablayout"
    private var tabLayout: ViewGroup?=null
    val NESTED_SCROLL = "nestedscrollview"
    private var nestedScrollView : NestedScrollView?=null

    private var scrollViewCriticalY: Int = 0
    private var scrollRawY :Float = 0F

    constructor()
    constructor(context: Context, attrs:AttributeSet):super(context,attrs)


    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        var handled =  super.onLayoutChild(parent, abl, layoutDirection)
        alphaViewGroup = parent.findViewWithTag(ALPHA_VIEWGROUP)
        toolbar = parent.findViewWithTag(TOOLBAR)
        tabLayout = parent.findViewWithTag(TABLAYOUT)
        nestedScrollView = parent.findViewWithTag(NESTED_SCROLL)
        if(scrollViewCriticalY==0)
            scrollViewCriticalY = toolbar?.height!!+tabLayout?.height!!

        LogUtils.e("toolbar.height: ${toolbar?.height}")
        LogUtils.e("alphaViewGroup.height: ${alphaViewGroup?.height}")
        LogUtils.e("scrollViewCriticalY: $scrollViewCriticalY")
        return handled
    }

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        if(scrollRawY ==0F)scrollRawY=nestedScrollView!!.y
        LogUtils.e("scrollRawY: $scrollRawY")
        return true
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        var curY = nestedScrollView!!.y
        var alpha = (scrollRawY-curY)/(scrollRawY-scrollViewCriticalY)
        LogUtils.e("alpha: $alpha")
        LogUtils.e("curY: $curY")
        alphaViewGroup?.alpha = alpha
        alphaViewGroup?.invalidate()
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

//    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
//        var curY = nestedScrollView!!.y
//        var alpha = (scrollRawY-curY)/(scrollRawY-scrollViewCriticalY)
//        LogUtils.e("alpha: $alpha")
//        LogUtils.e("curY: $curY")
//        alphaViewGroup?.alpha = alpha
//        alphaViewGroup?.invalidate()
//        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
//    }
}
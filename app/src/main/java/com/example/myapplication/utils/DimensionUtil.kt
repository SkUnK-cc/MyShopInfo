package com.example.myapplication.utils

import android.content.Context
import android.util.TypedValue
import android.view.View

object DimensionUtil {
    fun dp2px(context: Context, dp: Float): Float{
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.resources.displayMetrics)
        var scale = context.resources.displayMetrics.density
        return dp*scale+0.5F
    }

    fun sp2px(context: Context, sp: Float): Float{
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.resources.displayMetrics)
        var scale = context.resources.displayMetrics.scaledDensity
        return sp*scale+0.5F
    }
}
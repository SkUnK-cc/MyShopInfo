package com.example.myapplication.utils

import android.util.TypedValue
import android.view.View

object DimensionUtil {
    fun dp2dx(view: View, dp: Float): Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,view.resources.displayMetrics)
    }
}
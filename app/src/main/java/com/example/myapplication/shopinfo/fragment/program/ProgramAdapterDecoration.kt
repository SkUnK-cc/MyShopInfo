package com.example.myapplication.shopinfo.fragment.program

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.DimensionUtil

class ProgramAdapterDecoration: RecyclerView.ItemDecoration() {


    var mPaint: Paint? = null
    var mOffsetLeft:Int = 0
    var mOffsetTop = 0
    var mCircleRadius:Int = 0
    init {
        mOffsetLeft = 5
        mCircleRadius = 3*mOffsetLeft
        mPaint = Paint()
        mPaint?.isAntiAlias = true
        mPaint?.color = Color.parseColor("#FFFFFF")
        mPaint?.strokeWidth = 10F

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = mOffsetLeft
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        var count = parent.childCount
        for(i in 0 until count){
            var view = parent.getChildAt(i)
            var index = parent.getChildAdapterPosition(view)
            var dividerTop = view.top - mOffsetTop
            var dividerLeft = parent.paddingLeft
            var dividerBottom = view.bottom
            var dividerRight = parent.width - parent.paddingRight

            var circleX = dividerLeft + mOffsetLeft*3
            var circleY = dividerTop + DimensionUtil.dp2px(view.context, (23/2).toFloat())

            var lineTopX = circleX
            var lineTopY = dividerTop
            var lineBottomX = circleX
            var lineBottomY = dividerBottom
            mPaint?.color = Color.parseColor("#FFFFFF")
            c.drawLine(lineTopX.toFloat(), lineTopY.toFloat(), lineBottomX.toFloat(), lineBottomY.toFloat(),mPaint)
            mPaint?.color = Color.parseColor("#FFDF0B")
            c.drawCircle(circleX.toFloat(),circleY, mCircleRadius.toFloat(),mPaint)
        }
    }
}
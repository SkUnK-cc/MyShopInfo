package com.example.myapplication.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.utils.DimensionUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PileView extends ViewGroup {

    protected float verticalSpace;
    protected float pileWidth = 0;
    public static final int VISIBLE_COUNT = 3;
    private Context context;

    public PileView(Context context) {
        this(context,null,0);
    }

    public PileView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttr(context,attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PileLayout);
        verticalSpace = ta.getDimension(R.styleable.PileLayout_pilelayout_verticalSpace, DimensionUtil.INSTANCE.dp2px(getContext(),4));
        pileWidth = ta.getDimension(R.styleable.PileLayout_pilelayout_pileWidth,DimensionUtil.INSTANCE.dp2px(getContext(),10));
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;      //所有行中最长的一行的宽度
        int height = 0;     //所有行高度的总和
        int rawWidth = 0;   //当前这一行的宽度
        int rawHeight = 0;  //当前这一行的高度

        int rowIndex = 0;   //当前处理的item在当前行中的位置
        int count = getChildCount();
        for(int i=0;i<count;i++){
            View child = getChildAt(i);
            if(child.getVisibility()==GONE){
                //如果是最后一个，height加上当前这一行的高，也就是增加一行
                //width 为最大的宽度
                if(i==count-1){
                    height += rawHeight;
                    width = Math.max(width,rawWidth);
                }
                continue;
            }

            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            int childHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            if(rawWidth+childWidth - (rowIndex>0?pileWidth:0) > widthSpecSize){
                width = Math.max(width,rawWidth);
                rawWidth = childWidth;
                height += rawHeight + verticalSpace;
                rawHeight = childHeight;
                rowIndex = 0;
            }else{
                rawWidth += childWidth;
                if(rowIndex>0){
                    rawWidth -= pileWidth;
                }
                rawHeight = Math.max(rawHeight,childHeight);
            }
            if(i==count-1){
                width = Math.max(rawWidth,width);
                height += rawHeight;
            }
            rowIndex++;
        }
        setMeasuredDimension(widthSpecMode==MeasureSpec.EXACTLY?widthSpecSize:width+getPaddingLeft()+getPaddingRight(),
                heightSpecMode==MeasureSpec.EXACTLY?heightSpecSize:height+getPaddingTop()+getPaddingBottom());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewWidth = r-1;
        int leftOffset = getPaddingLeft();
        int topOffset = getPaddingTop();
        int rowMaxHeight = 0;
        int rowIndex = 0;
        View childView;
        for(int w=0,count=getChildCount();w<count;w++){
            childView = getChildAt(w);
            if(childView.getVisibility() == GONE)continue;
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int occupyWidth = lp.leftMargin+childView.getMeasuredWidth()+lp.rightMargin;
            if(leftOffset+occupyWidth+getPaddingRight()>viewWidth){
                leftOffset = getPaddingLeft(); //回到最右边
                topOffset += rowMaxHeight+verticalSpace; //换行
                rowMaxHeight = 0;
                rowIndex = 0;
            }

            int left = leftOffset + lp.leftMargin;
            int top = topOffset + lp.topMargin;
            int right = leftOffset+lp.leftMargin+childView.getMeasuredWidth();
            int bottom = topOffset + lp.topMargin + childView.getMeasuredHeight();
            childView.layout(left,top,right,bottom);

            leftOffset += occupyWidth;  //横向偏移
            int occupyHeight = lp.topMargin+childView.getMeasuredHeight()+lp.bottomMargin;//当前行最高
            if(rowIndex != count-1){
                leftOffset -= pileWidth;
            }
            rowMaxHeight = Math.max(rowMaxHeight,occupyHeight);
            rowIndex++;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    public void setAvertImage(List<String> imageList){
        setAvertImages(imageList,VISIBLE_COUNT);
    }

    private void setAvertImages(List<String> imageList, int visibleCount) {
        List<String> visibleList = null;
        if(imageList.size()>visibleCount){
            visibleList = imageList.subList(imageList.size()-1-visibleCount,imageList.size()-1);
        }
        removeAllViews();
        for(int i=0;i<visibleList.size();i++){
            CircleImageView image = (CircleImageView) LayoutInflater.from(context).inflate(R.layout.pileview_item,this,false);
            addView(image);
        }
    }

    public void setAvertImages(int visibleCount) {
        for(int i=0;i<visibleCount;i++){
            CircleImageView image = (CircleImageView) LayoutInflater.from(context).inflate(R.layout.pileview_item,this,false);
            addView(image);
        }
    }
}

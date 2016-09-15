package com.example.u17.base_cusview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eline on 2016/5/19.
 */
public class MyFlowLayout extends ViewGroup implements View.OnClickListener{
    private OnFlowLayoutListener onFlowLayoutListener;

    public void setOnFlowLayoutListener(OnFlowLayoutListener onFlowLayoutListener) {
        this.onFlowLayoutListener = onFlowLayoutListener;
    }

    public MyFlowLayout(Context context) {
        this(context,null);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int totalWidth = 0;
        int row = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setOnClickListener(this);
            childView.setTag(i);
            int measuredWidth = childView.getMeasuredWidth();
            int measuredHeight = childView.getMeasuredHeight();
            totalWidth= totalWidth+measuredWidth+20;
            if (totalWidth>(r-l)){
                row++;
                totalWidth = measuredWidth+20;
            }
            childView.layout(totalWidth-measuredWidth,(20+measuredHeight)*row,totalWidth,(20+measuredHeight)*row+measuredHeight);
        }
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (onFlowLayoutListener!=null){
            onFlowLayoutListener.onItemClick(v,position);
        }
    }
}


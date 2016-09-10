package com.example.u17.base_cusview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 彭永顺 on 2016/8/28.
 */
public class CusGridView extends GridView {
    public CusGridView(Context context) {
        this(context,null);
    }

    public CusGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}

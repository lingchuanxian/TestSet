/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/31.
 */
public class AutoBreakViewGroup extends ViewGroup {
    private int mScreenWidth;
    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public AutoBreakViewGroup(Context context) {
        super(context);
        init();
    }

    public AutoBreakViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setSpacing(int horizontalSpacing, int verticalSpacing) {
        mHorizontalSpacing = horizontalSpacing;
        mVerticalSpacing = verticalSpacing;
    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mTotalHeight = 0;
        int mTotalWidth = 0;
        int mTempHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            mTempHeight = (measureHeight > mTempHeight) ? measureHeight : mTempHeight;
            if ((measuredWidth + mTotalWidth + mHorizontalSpacing) > mScreenWidth) {
                mTotalWidth = 0;
                mTotalHeight += (mTempHeight + mVerticalSpacing);
                mTempHeight = 0;
            }
            childView.layout(mTotalWidth + mHorizontalSpacing, mTotalHeight, measuredWidth + mTotalWidth + mHorizontalSpacing, mTotalHeight + measureHeight);
            mTotalWidth += (measuredWidth + mHorizontalSpacing);
        }
    }
}

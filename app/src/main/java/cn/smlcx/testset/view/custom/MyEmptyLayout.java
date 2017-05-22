/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.smlcx.testset.R;

/**
 * Created by lcx on 2017/4/14.
 */

public class MyEmptyLayout extends LinearLayout {

    private Context mContext;
    private static final int state_loading = 1;
    private static final int state_empty = 2;
    private static final int state_error = 3;
    private View mLoadingView;
    private View mEmptyView;
    private View mBindView;
    private View mErrorView;
    private Button mBtnReset;
    private TextView mEmptyText;
    private TextView tvLoadingText;

    public MyEmptyLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public MyEmptyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyEmptyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        //加载中的布局
        mLoadingView = View.inflate(mContext, R.layout.layout_loading, null);
        tvLoadingText = (TextView) mLoadingView.findViewById(R.id.tvLoadingText);
        addView(mLoadingView,params);

        //为空时的布局
        mEmptyView = View.inflate(mContext, R.layout.layout_empty, null);
        mEmptyText = (TextView) mEmptyView.findViewById(R.id.tvEmptyText);
        addView(mEmptyView,params);

        //错误时的布局
        mErrorView = View.inflate(mContext, R.layout.layout_error, null);
        mBtnReset = (Button) mErrorView.findViewById(R.id.btnReset);
        addView(mErrorView,params);

    }

    public void setEmptyStatus(int emptyStatus) {
        _switchEmptyView(emptyStatus);
    }

    /**
     * 切换视图
     */
    private void _switchEmptyView(int emptyStatus) {
        switch (emptyStatus) {
            case state_loading:
                break;
            case state_empty:
                break;
            case state_error:
                break;
        }
    }

}

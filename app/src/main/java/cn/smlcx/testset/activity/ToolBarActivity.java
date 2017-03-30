/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.testset.R;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ToolBarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initViews() {
        mToolbar.inflateMenu(R.menu.zhihu_toolbar_menu);

        mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);

        mToolbar.setTitle("首页");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_toolbar;
    }

}

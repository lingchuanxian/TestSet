/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.view.View;

import butterknife.OnClick;
import cn.smlcx.testset.R;

/**
 * Created by lcx on 2017/4/14.
 */

public class MaterialDialogsActivity extends BaseActivity {

    @OnClick({R.id.easydialog})
    void click(View view){
        switch (view.getId()){
            case R.id.easydialog:
                break;
        }
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_material_dialogs;
    }
}

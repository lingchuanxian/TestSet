/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.testset.R;
import cn.smlcx.testset.view.custom.LockView;

/**
 * Created by Administrator on 2017/3/28.
 */

public class LockViewActivity extends BaseActivity {
    @BindView(R.id.lockView)
    LockView mLockView;
    @BindView(R.id.msg)
    TextView mMsg;

    @Override
    protected void initViews() {
        mLockView.setrOnLockChangeListener(new LockView.OnLockChangeListener() {
            @Override
            public void setLockSuccessed(String password) {
                Toast.makeText(LockViewActivity.this,password,Toast.LENGTH_LONG).show();
                mMsg.setText("密码为："+password);
            }

            @Override
            public void setLockError() {
                Toast.makeText(LockViewActivity.this,"密码太短",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lockview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

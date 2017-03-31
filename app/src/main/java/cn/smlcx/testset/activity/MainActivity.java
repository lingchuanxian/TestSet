/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smlcx.testset.R;
import cn.smlcx.testset.view.custom.AutoBreakViewGroup;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.regist)
    Button mRegist;
    @BindView(R.id.exit)
    Button mExit;
    @BindView(R.id.autoBreakViewGroup)
    AutoBreakViewGroup mAutoBreakViewGroup;
    private ArrayList<String> mData = new ArrayList<>();
    private Context mContext;

    @OnClick({R.id.login, R.id.regist, R.id.lock, R.id.toolbar, R.id.switchBtn, R.id.attributes, R.id.exit})
    void registOnClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent1 = new Intent(this, SecondActivity.class);
                startActivity(intent1);
                break;
            case R.id.regist:
                Intent intent2 = new Intent(this, ThirdActivity.class);
                startActivity(intent2);
                break;
            case R.id.lock:
                Intent intent3 = new Intent(this, LockViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.toolbar:
                Intent intent4 = new Intent(this, ToolBarActivity.class);
                startActivity(intent4);
                break;
            case R.id.switchBtn:
                Intent intent5 = new Intent(this, SwitchActivity.class);
                startActivity(intent5);
                break;
            case R.id.attributes:
                Intent intent6 = new Intent(this, AttributeActivity.class);
                startActivity(intent6);
                break;
            case R.id.exit:
                Log.e("login", "点击退出");
                app.getInstance().finishActivity();
                break;
            default:
                break;
        }

    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mContext = this;
        mAutoBreakViewGroup.setSpacing(20,10);
        /*mData.add("隔帘花影");
        mData.add("国色天香");
        mData.add("快穿");
        mData.add("空空幻");
        mData.add("总裁");
        mData.add("诛仙");
        mData.add("旋风少女");
        mData.add("耳根");
        mData.add("系统");

        for (int i = 0; i < mData.size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setText(mData.get(i));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            textView.setTextColor(Color.BLACK);
            textView.setLayoutParams(new
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //textView.setBackgroundResource(R.drawable.item_bg);
            textView.setBackgroundColor(Color.WHITE);
            textView.setPadding(20, 10, 20, 10);
            textView.setGravity(Gravity.CENTER);
            mAutoBreakViewGroup.addView(textView);
        }*/
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

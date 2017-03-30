/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.DeviceUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smlcx.testset.R;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.regist)
    Button mRegist;
    @BindView(R.id.exit)
    Button mExit;

    @OnClick({R.id.login,R.id.regist,R.id.lock,R.id.toolbar,R.id.switchBtn,R.id.attributes,R.id.exit})
    void registOnClick(View view) {
        switch (view.getId()){
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
        Log.e("Main", "initViews: "+ DeviceUtils.getMacAddress());
    }

    @Override
    protected void initDatas() {

    }
}

/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smlcx.testset.R;
import cn.smlcx.testset.view.custom.AutoBreakViewGroup;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    protected final String TAG = this.getClass().getSimpleName();
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

    @OnClick({R.id.login, R.id.regist, R.id.lock, R.id.toolbar, R.id.switchBtn, R.id.attributes,R.id.materialdialogs,R.id.recyclerview, R.id.exit})
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
            case R.id.materialdialogs:
                Intent intent8 = new Intent(this, MaterialDialogsActivity.class);
                startActivity(intent8);
                break;
            case R.id.recyclerview:
                Intent intent7 = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent7);
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

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取成功");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取失败");
                        }
                    }
                });
        if(rxPermissions.isGranted(Manifest.permission.CAMERA)){
            Log.i(TAG, "initViews: 已获取权限");
        }else{
            Log.i(TAG, "initViews: 未获取权限");
            gotoMiuiPermission();
        }
    }

    @Override
    protected void initDatas() {

    }
    private void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(localIntent);
    }

    /**
     * 跳转到miui的权限管理页面
     */
    private void gotoMiuiPermission() {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", getPackageName());
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

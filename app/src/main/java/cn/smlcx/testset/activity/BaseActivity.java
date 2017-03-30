package cn.smlcx.testset.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

import butterknife.ButterKnife;
import cn.smlcx.testset.app.ApplicationEx;
import cn.smlcx.testset.view.IBaseView;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2017/3/11.
 */

public abstract class BaseActivity extends Activity implements IBaseView{
    public ApplicationEx app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        app = (ApplicationEx) getApplication();
        app.addActivity(this);
        Utils.init(this);
        EventBus.getDefault().register(this);
        initViews();
        initDatas();
    }

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    public void onEventMainThread(Integer i){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.finishActivity(this);
        EventBus.getDefault().unregister(this);
        Log.e("wing","main finished");
    }
}

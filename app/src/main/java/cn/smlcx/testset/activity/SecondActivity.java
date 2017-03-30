package cn.smlcx.testset.activity;

import android.util.Log;
import android.widget.ProgressBar;

import butterknife.OnClick;
import cn.smlcx.testset.R;
import de.greenrobot.event.EventBus;

import static com.github.ybq.android.library.R.id.DoubleBounce;


/**
 * Created by Administrator on 2017/3/11.
 */

public class SecondActivity extends BaseActivity {

    @Override
    protected void initViews() {
       /* ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);*/
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_second;
    }
}

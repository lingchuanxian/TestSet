package cn.smlcx.testset.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smlcx.testset.R;
import cn.smlcx.testset.view.custom.EmptyLayout;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ThirdActivity extends BaseActivity {
    @BindView(R.id.textHello)
    TextView mTextHello;
    @BindView(R.id.emptyLayout)
    EmptyLayout mEmptyLayout;

    @Override
    protected void initViews() {
        mEmptyLayout.showLoading();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_third;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

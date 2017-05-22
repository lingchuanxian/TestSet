/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.smlcx.testset.R;
import cn.smlcx.testset.adapter.MyAdapter;

import static cn.smlcx.testset.R.id.recyclerview;

/**
 * Created by lcx on 2017/4/1.
 */

public class RecyclerViewActivity extends BaseActivity {

    private LinearLayoutManager mLayoutManager;
    private ArrayList<String> mDataList;
    private MyAdapter myAdapter;
    @BindView(recyclerview)
    RecyclerView mRecyclerview;

    @Override
    protected void initViews() {
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initDatas() {
        mDataList = new ArrayList<String>();
        for (int i=0;i<50;i++){
            mDataList.add("内容 - "+i);
        }
        myAdapter = new MyAdapter(mDataList);
        mRecyclerview.setAdapter(myAdapter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_recycleview;
    }



}

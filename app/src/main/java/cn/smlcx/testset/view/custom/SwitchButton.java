/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.smlcx.testset.R;

/**
 * Created by Administrator on 2017/3/29.
 */

public class SwitchButton extends View implements View.OnClickListener {
    private Bitmap btn_switch;
    private Bitmap btn_slip;
    private Paint mPaint;
    private int curSlip,slipMax;
    private boolean isOpen = false;
    private float firPointX;
    private boolean isEnableClick = true;
    private  float lastX;

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        btn_switch = BitmapFactory.decodeResource(getResources(), R.mipmap.btn_switch);
        btn_slip = BitmapFactory.decodeResource(getResources(), R.mipmap.btn_slip);
        slipMax = btn_switch.getWidth() - btn_slip.getWidth();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(btn_switch.getWidth(),btn_slip.getWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(btn_switch,0,0,mPaint);
        canvas.drawBitmap(btn_slip,curSlip,0,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = firPointX = event.getX();
                isEnableClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float secPoint = event.getX();
                float distance = secPoint - firPointX;
                curSlip+=distance;

                if(curSlip < 0){
                    curSlip = 0;
                }else if(curSlip > slipMax){
                    curSlip = slipMax;
                }
                invalidate();
                firPointX = event.getX();
                if(Math.abs(secPoint - lastX) > 5){
                    isEnableClick = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isEnableClick) {
                    if(curSlip > slipMax/2){
                        isOpen = true;
                    }else {
                        isOpen = false;
                    }
                    freshView();
                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (isEnableClick) {
            isOpen = !isOpen;
            freshView();
        }
    }

    private void freshView() {
        if(!isOpen){
            curSlip = 0;
        }else{
            curSlip = slipMax;
        }
        invalidate();
    }
}

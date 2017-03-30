/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.smlcx.testset.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.smlcx.testset.R;

/**
 * Created by Administrator on 2017/3/30.
 */

public class AttributeView extends View {
    private int age;
    private String name;
    private Bitmap bg;
    public AttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //获取属性的三种方式
        //用命名空间获取
       /* String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","lcx_age");
        String name =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","lcx_name");
        String bg =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","lcx_bg");
        System.out.print("age"+age+"name"+name+"bg"+bg);*/
        //遍历
        for (int i = 0; i <attrs.getAttributeCount() ; i++) {
            System.out.print(attrs.getAttributeName(i)+":"+attrs.getAttributeValue(i));
        }
        //使用系统工具
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeView);
        for (int i = 0; i < typeArray.getIndexCount(); i++) {
            int index = typeArray.getIndex(i);
            switch (index){
                case  R.styleable.AttributeView_lcx_age:
                    age = typeArray.getInt(index,0);
                    break;
                case  R.styleable.AttributeView_lcx_name:
                    name = typeArray.getString(index);
                    break;
                case  R.styleable.AttributeView_lcx_bg:
                    BitmapDrawable bitmap = (BitmapDrawable) typeArray.getDrawable(index);
                    bg = bitmap.getBitmap();
                    break;
            }
        }

        typeArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setTextSize(40);
        canvas.drawBitmap(bg,50,50,mPaint);
        canvas.drawText("我叫"+name+"，今年已经"+age+"了。很高兴认识大家。",50,50,mPaint);
    }
}

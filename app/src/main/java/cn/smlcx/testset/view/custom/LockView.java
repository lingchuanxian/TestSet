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
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.smlcx.testset.R;

/**
 * Created by Administrator on 2017/3/28.
 */

public class LockView extends View {
    private boolean isInit = false;
    private int width,height;
    private Point[][] points;
    private Bitmap point_normal,point_pressed,point_erro;
    private float br;
    private boolean isSelected;
    private boolean movePoint = true;
    private boolean isFinish;
    private List<Point>  pointList = new ArrayList<Point>();
    private Paint mPaint = new Paint();
    private float eventX,eventY;
    private Point lastpoint;
    private Point middlePoint;
    private OnLockChangeListener onLockChangeListener;

    public LockView(Context context) {
        super(context);
    }

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInit){
            initPoint();
            initPaint();
            isInit = true;
        }
        drawPoints(canvas);
        drawLines(canvas);
    }

    private void initPaint() {
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(12);
    }

    private void drawLines(Canvas canvas) {
        if (pointList.size()>0) {
            Point a = pointList.get(0);
            for (int i = 0; i < pointList.size(); i++) {
                Point b = pointList.get(i);
                canvas.drawLine(a.getX(),a.getY(),b.getX(),b.getY(),mPaint);
                a = b;
            }
            if(!movePoint){
                canvas.drawLine(a.getX(),a.getY(),eventX,eventY,mPaint);
            }
        }
    }

    private void drawPoints(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if(point.getState()==Point.STATE_NORMAL){
                    canvas.drawBitmap(point_normal,point.getX()-br,point.getY()-br,null);
                }else  if(point.getState()==Point.STATE_PRESSED){
                    canvas.drawBitmap(point_pressed,point.getX()-br,point.getY()-br,null);
                }else  if(point.getState()==Point.STATE_ERROR){
                    canvas.drawBitmap(point_erro,point.getX()-br,point.getY()-br,null);
                }
            }
        }

    }

    private void initPoint() {
        width = getWidth();
        height = getHeight();
        float offsetY = 0,offsetX = 0;
        if(width < height){//竖屏
            offsetY = (height-width)/2;
            height = width;
        }else{//横屏
            offsetX = (width-height)/2;
            width = height;
        }
        //定义九个点的坐标
        points = new Point[3][3];
        int index = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3 ; j++) {
                points[i][j] = new Point(offsetX+(width/4)*(i+1),offsetY+(width/4)*(j+1));
                points[i][j].setIndex(index);
                index++;
            }
        }
        //实例化三张图片
        point_normal = BitmapFactory.decodeResource(getResources(), R.drawable.point1);
        point_pressed = BitmapFactory.decodeResource(getResources(), R.drawable.point3);
        point_erro = BitmapFactory.decodeResource(getResources(), R.drawable.point2);

        br = point_normal.getWidth() / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isFinish = false;
        movePoint = true;
        eventX = event.getX();
        eventY = event.getY();
        Point checkPoint = null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
                reset();
                checkPoint = CheckPoint(eventX,eventY,br);
                if (checkPoint != null) {
                    isSelected = true;
                    checkPoint.setState(Point.STATE_PRESSED);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSelected) {
                    checkPoint = CheckPoint(eventX,eventY,br);
                    if (checkPoint != null) {
                        if(!pointList.contains(checkPoint)){
                            middlePoint = CheckPoint((lastpoint.getX()+checkPoint.getX())/2,(lastpoint.getY()+checkPoint.getY())/2,br);
                            if (middlePoint != null) {
                                middlePoint.setState(Point.STATE_PRESSED);
                            }
                        }
                        checkPoint.setState(Point.STATE_PRESSED);
                        movePoint = true;
                    }else{
                        movePoint = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFinish = true;
                isSelected = false;
                break;
        }
        if (isSelected&&!isFinish&&checkPoint!=null) {
            if (middlePoint != null&&!pointList.contains(middlePoint)) {
                pointList.add(middlePoint);
            }
            if (!pointList.contains(checkPoint)) {
                lastpoint = checkPoint;
                pointList.add(checkPoint);
            }else{
                movePoint = false;
            }
        }else if(isFinish){
            if (pointList != null) {
                if (pointList.size() == 1) {
                    reset();
                }else if(pointList.size() < 5){
                    errorPoint();
                    onLockChangeListener.setLockError();
                }else if (pointList.size() >= 5) {
                    if (onLockChangeListener != null) {
                        StringBuilder password = new StringBuilder();
                        for (int i = 0; i < pointList.size(); i++) {
                                int index = pointList.get(i).getIndex();
                                password.append(index + "");
                        }
                        if (onLockChangeListener != null) {
                            onLockChangeListener.setLockSuccessed(password.toString());
                        }
                    }
                }
            }
        }
        //刷新onDraw
        postInvalidate();
        return true;
    }

    private void errorPoint() {
        for (Point point : pointList) {
            point.setState(Point.STATE_ERROR);
        }
    }

    //重置九宫格所有的点
    private void reset() {
        for (Point point : pointList) {
            point.setState(Point.STATE_NORMAL);
        }
        pointList.clear();
        middlePoint = null;
    }

    /**
     * 
     * @param eventX
     * @param eventY
     * @param br
     * @return
     */
    private Point CheckPoint(float eventX, float eventY, float br) {
        for (int i = 0; i <points.length ; i++) {
            for (int j = 0; j <points[i].length ; j++) {
                Point point = points[i][j];
                double distance = getDistance(point.getX(),point.getY(),eventX,eventY);
                if(distance < br){
                    return point;
                }
            }
        }
        return null;
    }

    private double getDistance(float x, float y, float x1, float y1) {
       return Math.sqrt(Math.abs(x-x1)*Math.abs(x-x1)+Math.abs(y-y1)*Math.abs(y-y1));
    }

    public interface OnLockChangeListener{
        void setLockSuccessed(String password);
        void setLockError();
    }
    
    public void setrOnLockChangeListener(OnLockChangeListener onLockChangeListener){
        this.onLockChangeListener = onLockChangeListener;
    }

}

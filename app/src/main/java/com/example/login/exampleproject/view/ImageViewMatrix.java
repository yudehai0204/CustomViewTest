package com.example.login.exampleproject.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.login.exampleproject.R;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ImageViewMatrix extends ImageView {
    private static final int MODE_NONE = 0x00123;// 默认的触摸模式
    private static final int MODE_DRAG = 0x00321;// 拖拽模式
    private static final int MODE_ZOOM = 0x00132;// 缩放or旋转模式
    private Matrix currentMatrix,saveMatrix;
    private PointF start, mid;// 起点、中点对象
    private int mode;// 当前的触摸模式
    private boolean UpPoint = false;
    private float preMove = 1F;// 上一次手指移动的距离
    private float saveRotate = 0F;// 保存了的角度值
    private float rotate = 0F;// 旋转的角度

    private float[] preEventCoor;// 上一次各触摸点的坐标集合

    public ImageViewMatrix(Context context) {
        this(context,null);
    }

    public ImageViewMatrix(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageViewMatrix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentMatrix = new Matrix();
        saveMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();
        mode = MODE_NONE;
        Point point = new Point();//x为宽,y为高
        ((Activity)context).getWindowManager().getDefaultDisplay().getSize(point);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_01);
        float degree = ((float) point.x)/bitmap.getWidth();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth()*degree), (int) (bitmap.getHeight()*degree), true);
        setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()& MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                mode= MODE_DRAG;
                saveMatrix.set(currentMatrix);
                start.set(event.getX(), event.getY());
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN://第二个触摸点按下
                preMove = calSpacing(event);

                if(preMove>10F){//进入旋转缩放模式
                    saveMatrix.set(currentMatrix);
                    mode = MODE_ZOOM;
                    calMidPoint(mid,event);
                }
                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getX(1);
                preEventCoor[2] = event.getY(0);
                preEventCoor[3] = event.getY(1);
                saveRotate = calRotation(event);
                break;
            case MotionEvent.ACTION_MOVE:
                 /*
             * 单点触控拖拽平移
             */
                if (mode == MODE_DRAG) {
                    currentMatrix.set(saveMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    currentMatrix.postTranslate(dx, dy);
                }
            /*
             * 两点触控拖放旋转
             */
                else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {
                    float currentMove = calSpacing(event);
                    currentMatrix.set(saveMatrix);
                /*
                 * 指尖移动距离大于10F缩放
                 */
                    if (currentMove > 10F) {
                        float scale = currentMove / preMove;
                        currentMatrix.postScale(scale, scale, mid.x, mid.y);
                    }
                /*
                 * 保持两点时旋转
                 */
                    if (preEventCoor != null) {
                        rotate = calRotation(event);
                        float r = rotate - saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mode = MODE_NONE;
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                preEventCoor = null;
                UpPoint = true;
                break;

        }
        setImageMatrix(currentMatrix);
        if(UpPoint){
            mode=MODE_DRAG;
            UpPoint=false;
            saveMatrix.set(currentMatrix);
        }
        return true;
    }
    /**
     * 计算两个触摸点间的距离
     */
    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 计算两个触摸点的中点坐标
     */
    private void calMidPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 计算旋转角度
     *
     * @param
     * @return 角度值
     */
    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }
}

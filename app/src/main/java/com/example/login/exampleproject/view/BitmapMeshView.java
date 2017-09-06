package com.example.login.exampleproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.login.exampleproject.R;

/**
 * Created by Administrator on 2017/7/13.
 */

public class BitmapMeshView extends View {
    private final int Width=19,Height=19;
    private final int COUNT = (Width+1)*(Height+1);
    private Bitmap mBitmap;// 位图资源

    private float[] verts;// 交点的坐标数组
    public BitmapMeshView(Context context) {
        this(context,null);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        verts = new float[COUNT*2];
        /*
         * 生成各个交点坐标
         */
        int index = 0;
        for (int y = 0; y <= Height; y++) {
            float fy = mBitmap.getHeight() * y / Height;
            for (int x = 0; x <= Width; x++) {
                float fx = mBitmap.getWidth() * x / Width ;
                setXY(fx, fy, index);
                index += 1;
            }
        }
    }

    /**
     * 将计算后的交点坐标存入数组
     *
     * @param fx
     *            x坐标
     * @param fy
     *            y坐标
     * @param index
     *            标识值
     */
    private void setXY(float fx, float fy, int index) {
        verts[index*2  + 0] = fx;
        verts[index*2  + 1] = fy;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制网格位图
        canvas.drawBitmapMesh(mBitmap, Width, Height, verts, 0, null, 0, null);
    }
}

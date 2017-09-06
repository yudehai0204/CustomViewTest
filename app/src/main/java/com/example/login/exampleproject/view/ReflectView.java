package com.example.login.exampleproject.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.login.exampleproject.R;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ReflectView extends View {
    private Bitmap mSrcBitmap,mRefBitmap;
    private Paint mPaint;
    private int x,y;
    private PorterDuffXfermode mXfermode;
    public ReflectView(Context context) {
        this(context,null);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_01);
        Matrix matrix = new Matrix();
        matrix.setScale(1f,-1f);

        mRefBitmap = Bitmap.createBitmap(mSrcBitmap,0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),matrix,true);
        Point point = new Point();//x为宽,y为高
        ((Activity)context).getWindowManager().getDefaultDisplay().getSize(point);

        x = point.x/2 - mSrcBitmap.getWidth()/2;
        y = point.y/2 - mSrcBitmap.getHeight();
        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(x,y+mSrcBitmap.getHeight(),x,
                y + mSrcBitmap.getHeight() + mSrcBitmap.getHeight() , 0xAA000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, x, y, null);

        int sc = canvas.saveLayer(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight(), null);

        mPaint.setXfermode(mXfermode);

        canvas.drawRect(x, y + mSrcBitmap.getHeight(), x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2+100, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(sc);
    }
}

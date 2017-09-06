package com.example.login.exampleproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/7/18.
 * 贝赛尔曲线
 */

public class QueToView extends View {
    private Path mPath;
    private Paint mPaint;
    private float mbottomx,mbottomy;
    private float mtopx,mtopy;
    private float pointx,pointy;//基点坐标
    private int width,height;
    private boolean isInc;
    public QueToView(Context context) {
        this(context,null);
    }

    public QueToView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QueToView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w ;
        height = h;
        mbottomy=width/4F;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(-1/4F*width,pointy);
        mPath.cubicTo(1/4F*width+mbottomx,mbottomy,mbottomx+width/2,mbottomy-width/2,width+1/4F*width,pointy);

        mPath.lineTo(width + 1 / 4F * width, height);
        mPath.lineTo(-1 / 4F * width, height);
        mPath.close();
        canvas.drawPath(mPath,mPaint);
  /*
//         * 当控制点的x坐标大于或等于终点x坐标时更改标识值
//         */
        if (mbottomx+width/2 >= width + 1 / 4F * width) {
            isInc = false;
        }
        /*
         * 当控制点的x坐标小于或等于起点x坐标时更改标识值
         */
        else if (mbottomx <= -1 / 4F * width) {
            isInc = true;
        }

        // 根据标识值判断当前的控制点x坐标是该加还是减
        mbottomx = isInc ? mbottomx + 10 : mbottomx - 10;

        /*
         * 让“水”不断减少
         */
        if (mbottomy <= height) {
            mbottomy += 2;
            pointy += 2;
        }
        mPath.reset();
        invalidate();
    }
}

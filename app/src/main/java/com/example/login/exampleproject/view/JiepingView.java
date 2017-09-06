package com.example.login.exampleproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by 于德海 on 2017/9/6.
 * 因变量命名较为直白，相关注释就省略了。
 *
 * @description
 */

public class JiepingView extends ViewGroup {
    public JiepingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiepingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public JiepingView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("JiepinEvent",event.getAction()+"");

        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}

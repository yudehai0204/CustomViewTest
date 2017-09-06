package com.example.login.exampleproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.login.exampleproject.view.BitmapMeshView;
import com.example.login.exampleproject.view.BitmapMeshView2;
import com.example.login.exampleproject.view.CanvasView;
import com.example.login.exampleproject.view.ImageViewMatrix;
import com.example.login.exampleproject.view.PathView;
import com.example.login.exampleproject.view.ReflectView;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SecondActivtiy extends Activity {
    private View view;
    int tag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        switch (getIntent().getIntExtra("tag",0)){
            case 1:
                ReflectView mView = (ReflectView) findViewById(R.id.view1);
                mView.setVisibility(View.VISIBLE);
                break;
            case 2:
                ImageViewMatrix matrix = (ImageViewMatrix) findViewById(R.id.view2);
                matrix.setVisibility(View.VISIBLE);
                break;
            case 3:
                BitmapMeshView meshView = (BitmapMeshView) findViewById(R.id.view3);
                meshView.setVisibility(View.VISIBLE);
                break;
            case 4:
                BitmapMeshView2 meshView1 = (BitmapMeshView2) findViewById(R.id.view4);
                meshView1.setVisibility(View.VISIBLE);
                break;
            case 5:
                CanvasView canvasView = (CanvasView) findViewById(R.id.view5);
                canvasView.setVisibility(View.VISIBLE);
                break;
            case 6:
                view= (PathView) findViewById(R.id.view6);
                view.setVisibility(View.VISIBLE);
                break;
            case 7:
                view=  findViewById(R.id.view7);
                view.setVisibility(View.VISIBLE);

                break;
            case 8:
                ViewGroup view8= (ViewGroup) findViewById(R.id.view8);
                view8.setVisibility(View.VISIBLE);
                break;
        }
        }
}

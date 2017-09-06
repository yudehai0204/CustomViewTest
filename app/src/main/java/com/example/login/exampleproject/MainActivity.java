package com.example.login.exampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn  = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn3);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn4);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn5);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn6);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn7);
        btn.setOnClickListener(this);
        btn  = (Button) findViewById(R.id.btn8);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this,SecondActivtiy.class);
        switch (v.getId()){
            case R.id.btn1:

                    intent.putExtra("tag",1);
                    startActivity(intent);
                break;
            case R.id.btn2:
                intent.putExtra("tag",2);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent.putExtra("tag",3);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent.putExtra("tag",4);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent.putExtra("tag",5);
                startActivity(intent);
                break;
            case R.id.btn6:
                intent.putExtra("tag",6);
                startActivity(intent);
                break;
            case R.id.btn7:
                intent.putExtra("tag",7);
                startActivity(intent);
                break;
        }
    }
}

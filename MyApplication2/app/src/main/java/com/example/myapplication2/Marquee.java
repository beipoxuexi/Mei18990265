package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication2.R;

public class Marquee extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_marquee;//声明一个文本视图对象
    private boolean isPaused=false;//跑马灯文字是否暂停滚动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);

        //从布局文件中获取名叫tv_marquee的文本视图
        tv_marquee=findViewById(R.id.tv_marquee);
        //给tv_marquee设置点击监听器
        tv_marquee.setOnClickListener(this);
        tv_marquee.requestFocus();//强制获得焦点
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.tv_marquee){
            isPaused=!isPaused;
            if(isPaused){
                tv_marquee.setFocusable(false);
                tv_marquee.setFocusableInTouchMode(false);
            }
            else {
                tv_marquee.setFocusable(true);
                tv_marquee.setFocusableInTouchMode(true);
                tv_marquee.requestFocus();
            }
        }
    }
}

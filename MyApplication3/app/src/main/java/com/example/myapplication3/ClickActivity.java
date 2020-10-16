package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        Button btn_click =findViewById(R.id.btn_center);
        btn_click.setOnClickListener(new MyOnClickListener());

        Button btn_click1 =findViewById(R.id.btn_fitCenter);
        btn_click1.setOnClickListener(new MyOnClickListener());

        Button btn_click2 =findViewById(R.id.btn_centerCrop);
        btn_click2.setOnClickListener(new MyOnClickListener());

        Button btn_click3 =findViewById(R.id.btn_centerInside);
        btn_click3.setOnClickListener(new MyOnClickListener());

        Button btn_click4 =findViewById(R.id.btn_fitXY);
        btn_click4.setOnClickListener(new MyOnClickListener());

        Button btn_click5 =findViewById(R.id.btn_fitStart);
        btn_click5.setOnClickListener(new MyOnClickListener());

        Button btn_click6 =findViewById(R.id.btn_fitEnd);
        btn_click6.setOnClickListener(new MyOnClickListener());

//        btn_click.setOnLongClickListener(new MyOnLongClickListener());

    }

    class MyOnClickListener implements View.OnClickListener{

        public void onClick(View v){
            ImageView iv_scale =findViewById(R.id.imageView3);;

            if(v.getId() == R.id.btn_center){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER);
            }else if(v.getId() ==  R.id.btn_fitCenter){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }else if(v.getId() ==  R.id.btn_centerCrop){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else if(v.getId() ==  R.id.btn_centerInside){
                iv_scale.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else if(v.getId() ==  R.id.btn_fitXY){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_XY);
            }else if(v.getId() ==  R.id.btn_fitStart){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_START);
            }else if(v.getId() ==  R.id.btn_fitEnd){
                iv_scale.setScaleType(ImageView.ScaleType.FIT_END);
            }
        }


    }


}
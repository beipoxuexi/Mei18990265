package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



import android.content.SharedPreferences;

import com.example.myapplication4.bean.UserInfo;
import com.example.myapplication4.database.UserDBHelper;

import java.util.ArrayList;
import java.util.Map;


public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_password_first; // 声明一个编辑框对象
    private EditText et_password_second; // 声明一个编辑框对象
    private EditText et_verifycode; // 声明一个编辑框对象
    private String mVerifyCode; // 验证码
    private String mPhone; // 手机号码

    private UserDBHelper mHelper; // 声明一个用户数据库帮助器的对象

    SharedPreferences mContextSp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);

        // 从布局文件中获取名叫et_password_first的编辑框
        et_password_first = findViewById(R.id.et_password_first);
        // 从布局文件中获取名叫et_password_second的编辑框
        et_password_second = findViewById(R.id.et_password_second);
        // 从布局文件中获取名叫et_verifycode的编辑框
        et_verifycode = findViewById(R.id.et_verifycode);

        findViewById(R.id.btn_verifycode).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);

        // 从前一个页面获取要修改密码的手机号码
        mPhone = getIntent().getStringExtra("phone");

//        mShared = getSharedPreferences("share", MODE_PRIVATE);
        mContextSp = this.getSharedPreferences( "share", MODE_PRIVATE );
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_verifycode) { // 点击了“获取验证码”按钮
            if (mPhone == null || mPhone.length() < 11) {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            // 生成六位随机数字的验证码
            mVerifyCode = String.format("%06d", (int) ((Math.random() * 9 + 1) * 100000));
            // 弹出提醒对话框，提示用户六位验证码数字
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请记住验证码");
            builder.setMessage("手机号" + mPhone + "，本次验证码是" + mVerifyCode + "，请输入验证码");
            builder.setPositiveButton("好的", null);
            AlertDialog alert = builder.create();
            alert.show();
        } else if (v.getId() == R.id.btn_confirm) { // 点击了“确定”按钮


            String password_first = et_password_first.getText().toString();
            String password_second = et_password_second.getText().toString();
            if (password_first.length() < 6 || password_second.length() < 6) {
                Toast.makeText(this, "请输入正确的新密码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password_first.equals(password_second)) {
                Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!et_verifycode.getText().toString().equals(mVerifyCode)) {
                Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            } else {

                SharedPreferences shared = getSharedPreferences("share", MODE_PRIVATE);
                Map<String, Object> mapParam = (Map<String, Object>) shared.getAll();

                String input_password=password_first;

                SharedPreferences.Editor editor = mContextSp.edit();

                for (Map.Entry<String, Object> item_map : mapParam.entrySet()) {
                    String key = item_map.getKey(); // 获取该配对的键信息

                    if (key.equals("pwd")) {
                        editor.putString( "pwd", input_password );
                        editor.commit();
                    }
                }




//
//                ArrayList<UserInfo> userArray = new ArrayList<UserInfo>();

//                userArray = mHelper.query("1=1");

////
//
//                for (int i = 0; i < userArray.size(); i++) {
//                    UserInfo info = userArray.get(i);
//
//                    if(info.phone.equals(mPhone)){
//                        info.pwd=password_first;
//                        mHelper.openWriteLink();
//                        mHelper.update(info,"pwd="+info.pwd);
//                    }
//                }

                mHelper=UserDBHelper.getInstance(this,2);
                mHelper.openWriteLink();
                UserInfo info=new UserInfo();
                info.pwd=password_first;
                mHelper.update(info,"pwd="+info.pwd);
                mHelper.closeLink();



                Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                // 把修改好的新密码返回给前一个页面
                Intent intent = new Intent();
                intent.putExtra("new_password", password_first);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication4.util.DateUtil;
import com.example.myapplication4.util.ViewUtil;

import android.content.SharedPreferences;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Switch sw_ios; // 声明一个开关按钮对象
    private TextView tv_ios_result; // 声明一个文本视图对象



    private RadioGroup rg_login; // 声明一个单选组对象
    private RadioButton rb_password; // 声明一个单选按钮对象
    private RadioButton rb_verifycode; // 声明一个单选按钮对象
    private EditText et_phone; // 声明一个编辑框对象
    private TextView tv_password; // 声明一个文本视图对象
    private EditText et_password; // 声明一个编辑框对象
    private Button btn_forget; // 声明一个忘记密码按钮控件对象
    private Button btn_login; // 声明一个登录按钮控件对象
    private Button btn_logon; // 声明一个注册按钮控件对象
//    private CheckBox ck_remember; // 声明一个复选框对象


    private EditText ct_name;
    private EditText ct_phonenumber;
    private EditText ct_password;
    private SharedPreferences mShared; // 声明一个共享参数对象


    private int mRequestCode = 0; // 跳转页面时的请求代码
    private int mType = 2; // 用户类型
    private boolean bRemember = false; // 是否记住密码
    private String mPassword = "111111"; // 默认密码
    private String mVerifyCode; // 验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw_ios = findViewById(R.id.sw_ios);
        // 从布局文件中获取名叫tv_result的文本视图
        tv_ios_result = findViewById(R.id.tv_ios_result);
//        sw_ios.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
//        refreshResult(sw_ios);


        rg_login = findViewById(R.id.rg_login);
        rb_password = findViewById(R.id.rb_password);
        rb_verifycode = findViewById(R.id.rb_verifycode);
        et_phone = findViewById(R.id.et_phone);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
        btn_forget = findViewById(R.id.btn_forget);
        btn_login = findViewById(R.id.btn_login);
        btn_logon = findViewById(R.id.btn_logon);
//        ck_remember = findViewById(R.id.ck_remember);


        mShared = getSharedPreferences("share", MODE_PRIVATE);



        // 给rg_login设置单选监听器
        rg_login.setOnCheckedChangeListener(new RadioListener());
        // 给et_phone添加文本变更监听器
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone));
        // 给et_password添加文本变更监听器
        et_password.addTextChangedListener(new HideTextWatcher(et_password));

        btn_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_logon.setOnClickListener(this);

        initTypeSpinner();
    }
    // 刷新Switch按钮的开关状态说明
//    private void refreshResult(CompoundButton buttonView) {
//        String result = String.format("Switch按钮的状态是%s",
//                (buttonView.isChecked()) ? "开" : "关");
//        if (buttonView.getId() == R.id.sw_ios) {
//            sw_ios.setText(result);
//        }
//
//    }

    // 初始化用户类型的下拉框
    private String[] typeArray = {"个人用户", "公司用户","18990265 梅杰"};

    private void initTypeSpinner() {
        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        // 设置数组适配器的布局样式
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        // 从布局文件中获取名叫sp_type的下拉框
        Spinner sp_type = findViewById(R.id.sp_type);
        // 设置下拉框的标题
        sp_type.setPrompt("请选择用户类型");
        // 设置下拉框的数组适配器
        sp_type.setAdapter(typeAdapter);
        // 设置下拉框默认显示第几项
        sp_type.setSelection(mType);

        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp_type.setOnItemSelectedListener(new TypeSelectedListener());
    }

    // 定义用户类型的选择监听器
    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        // 选择事件的处理方法，其中arg2代表选择项的序号
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            mType = arg2;
        }

        // 未选择时的处理方法，通常无需关注
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // 定义登录方式的单选监听器
    private class RadioListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_password) { // 选择了密码登录
                tv_password.setText("登录密码：");
                et_password.setHint("请输入密码");
                btn_forget.setText("忘记密码");
//                ck_remember.setVisibility(View.VISIBLE);
                sw_ios.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.rb_verifycode) { // 选择了验证码登录
                tv_password.setText("　验证码：");
                et_password.setHint("请输入验证码");
                btn_forget.setText("获取验证码");
//                ck_remember.setVisibility(View.INVISIBLE);
                sw_ios.setVisibility(View.INVISIBLE);
            }
        }
    }

    // 定义编辑框的文本变化监听器
    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        private CharSequence mStr;

        HideTextWatcher(EditText v) {
            super();
            mView = v;
            mMaxLength = ViewUtil.getMaxLength(v);
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mStr = s;
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {
            if (mStr == null || mStr.length() == 0)
                return;
            // 手机号码输入达到11位，或者密码/验证码输入达到6位，都关闭输入法软键盘
            if ((mStr.length() == 11 && mMaxLength == 11) ||
                    (mStr.length() == 6 && mMaxLength == 6)) {
                ViewUtil.hideOneInputMethod(MainActivity.this, mView);
            }
        }
    }

    @Override
    public void onClick(View v) {



        String phone = et_phone.getText().toString();

        if (v.getId() == R.id.btn_forget) { // 点击了“忘记密码”按钮
            if (phone.length() < 11) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rb_password.isChecked()) { // 选择了密码方式校验，此时要跳到找回密码页面
                Intent intent = new Intent(this, LoginForgetActivity.class);
                // 携带手机号码跳转到找回密码页面
                intent.putExtra("phone", phone);
                startActivityForResult(intent, mRequestCode);
            } else if (rb_verifycode.isChecked()) { // 选择了验证码方式校验，此时要生成六位随机数字验证码
                // 生成六位随机数字的验证码,结果用0填充
                mVerifyCode = String.format("%06d", (int) ((Math.random() * 9 + 1) * 100000));
                // 弹出提醒对话框，提示用户六位验证码数字
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请记住验证码");
                builder.setMessage("手机号" + phone + "，本次验证码是" + mVerifyCode + "，请输入验证码");
                builder.setPositiveButton("好的", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else if (v.getId() == R.id.btn_login) { // 点击了“登录”按钮
            if (phone.length() < 11) { // 手机号码不足11位
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (et_password.getText().length()==0&&et_phone.getText().length()==0){ // 密码方式校验

                mShared=getSharedPreferences("share", MODE_PRIVATE);
                //Map<String, Object> mapParam1 = (Map<String, Object>) shared1.getAll();

                //for (Map.Entry<String, Object> item_map : mapParam1.entrySet()) {
                //    String key = item_map.getKey(); // 获取该配对的键信息
                    String phone1=mShared.getString("phone", "");
                    String password1=mShared.getString("pwd", "");
                    et_phone.setText(phone1);
                    et_password.setText(password1);

                //}





                SharedPreferences shared = getSharedPreferences("share", MODE_PRIVATE);
                Map<String, Object> mapParam = (Map<String, Object>) shared.getAll();

                String input_password=et_password.getText().toString();
                String input_phone=et_phone.getText().toString();

                String remembered_password="";
                String remembered_phone="";

                for (Map.Entry<String, Object> item_map : mapParam.entrySet()) {
                    String key = item_map.getKey(); // 获取该配对的键信息
                    Object value = item_map.getValue(); // 获取该配对的值信息
                    if (key.equals("phone")) { // 如果配对值的类型为字符串
                        remembered_phone = shared.getString(key, "");
//                        desc = String.format("%s\n　%s的取值为%s", desc, key,
//                                shared.getString(key, ""));
                    }
                    if (key.equals("pwd")) {
                        remembered_password = shared.getString(key, "");
                    }
                }

                if (!et_password.getText().toString().equals(remembered_password) || !et_phone.getText().toString().equals(remembered_phone)) {
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                } else { // 密码校验通过
                    loginSuccess(); // 提示用户登录成功

                    //如果选择了记住密码
                    if(sw_ios.isChecked()){



                        ct_phonenumber = findViewById(R.id.et_phone);
                        ct_password = findViewById(R.id.et_password);



                        String phone_num = ct_phonenumber.getText().toString();
                        String pwd = ct_password.getText().toString();

                        SharedPreferences.Editor editor = mShared.edit(); // 获得编辑器的对象

                        editor.putString("phone", phone_num); // 添加一个名叫age的整型参数
                        editor.putString("pwd",pwd); // 添加一个名叫height的长整型参数
                        editor.putString("update_time", DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
                        editor.commit(); // 提交编辑器中的修改
                    }
                }
            } else if (rb_verifycode.isChecked()) { // 验证码方式校验
                if (!et_password.getText().toString().equals(mVerifyCode)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                } else { // 验证码校验通过
                    loginSuccess(); // 提示用户登录成功
                }
            }
        }
        else if (v.getId() == R.id.btn_logon){
            Intent intent = new Intent(this, WriteActivity.class);
            startActivity(intent);
        }
    }

    // 忘记密码修改后，从后一个页面携带参数返回当前页面时触发
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mRequestCode && data != null) {
            // 用户密码已改为新密码，故更新密码变量
            mPassword = data.getStringExtra("new_password");
        }
    }

    // 从修改密码页面返回登录页面，要清空密码的输入框
    @Override
    protected void onRestart() {
        et_password.setText("");
        super.onRestart();
    }

    // 校验通过，登录成功
    private void loginSuccess() {
        String desc = String.format("您的手机号码是%s，类型是%s。恭喜你通过登录验证，点击“确定”按钮返回上个页面",
                et_phone.getText().toString(), typeArray[mType]);
        // 弹出提醒对话框，提示用户登录成功
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("我再看看", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
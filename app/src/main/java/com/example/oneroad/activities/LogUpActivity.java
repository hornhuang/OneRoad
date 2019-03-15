package com.example.oneroad.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneroad.R;
import com.example.oneroad.users.User;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LogUpActivity extends AppCompatActivity implements View.OnClickListener {

    // 控件
    private Button mLogUp, mGetVerificationCode;
    private FloatingActionButton mLogIn;
    private EditText mUserPhoneNumber, mUserVerificationCode,
        mUserPassword, mUserPasswordAgain;

    // 信息
    private Intent mFrom, mTo;
    private boolean mFromLogIn;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        MobSDK.init(this);// Mod 开发平台短信服务使用权

        getIntentFromPrevious();
        iniclick();
    }

    private void getIntentFromPrevious(){
        mFrom = getIntent();
        if ( mFrom.getStringExtra("from").equals("LOGIN") ){
            mFromLogIn = false;
        }else{
            mFromLogIn = true;
        }
    }

    private void iniclick(){
        mLogIn = (FloatingActionButton) findViewById(R.id.log_up_log_in_button);
        mLogUp = (Button) findViewById(R.id.log_up_log_up_button);
        mUserPhoneNumber = (EditText) findViewById(R.id.log_up_input_phone_number);
        mUserVerificationCode = (EditText) findViewById(R.id.log_up_input_verification_code);
        mUserPassword = (EditText) findViewById(R.id.log_up_set_password);
        mUserPasswordAgain = (EditText) findViewById(R.id.log_up_set_password_again);
        mGetVerificationCode = (Button) findViewById(R.id.log_up_get_verification_code);

        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mGetVerificationCode.setOnClickListener(this);
    }

    private boolean getEditsIfo(){
        String string[] = new String[]{
                mUserPhoneNumber.getText().toString(),
                mUserVerificationCode.getText().toString(),
                mUserPassword.getText().toString(),
                mUserPasswordAgain.getText().toString(),
        };
        if ( string[0].equals("") ){
            Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
            return false;
        } else if ( string[1].equals("") ){
            Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return false;
        } else if ( string[2].equals("") ){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        } else if ( string[3].equals("") ){
            Toast.makeText(this,"请再次输入密码",Toast.LENGTH_SHORT).show();
            return false;
        } if ( !string[2].equals(string[3]) ){
            Toast.makeText(this,"两次输入密码不相同",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            user = new User( mUserPhoneNumber.getText().toString(), mUserPassword.getText().toString() );
            return true;
        }
    }

    // 发送验证码并进行验证
    public void sendCode(Context context, final String phoneNumber) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("86"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get(phoneNumber); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else{
                    // TODO 处理错误的结果
                    Toast.makeText(LogUpActivity.this,"验证码发送失败，请检查网络或手机号",Toast.LENGTH_SHORT).show();
                }
            }
        });
        page.show(context);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.log_up_log_in_button:
                if (mFromLogIn){
                    mTo = new Intent(  LogUpActivity.this, LogInActivity.class );
                    mTo.putExtra("from", "LOGUP");
                    startActivity( mTo );
                }else {
                    finish();
                }
                break;
            case R.id.log_up_log_up_button:
                // do something and finish
                if ( getEditsIfo() ){
                    finish();
                }
                break;
            case R.id.log_up_get_verification_code:
                // do something
                String phoneNumber = mUserPhoneNumber.getText().toString();
                if ( !phoneNumber.equals("") ){
                    sendCode(LogUpActivity.this, phoneNumber);
                } else {
                    Toast.makeText(LogUpActivity.this,"请输入手机号码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.log_up_user_avatar:
                // do something
                break;
        }
    }
}

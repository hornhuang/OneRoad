package com.example.oneroad.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oneroad.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogIn ;
    private FloatingActionButton mLogUp;
    private CircleImageView mUserAvatar;
    private TextView mForgetPassword;
    private EditText mPasswordCode, mVerificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        iniClick();
    }

    private void iniClick(){
        mLogIn = (Button) findViewById(R.id.log_in_log_in_button);
        mLogUp = (FloatingActionButton) findViewById(R.id.log_in_log_up_button);
        mUserAvatar = (CircleImageView) findViewById(R.id.log_in_user_avatar);
        mForgetPassword = (TextView) findViewById(R.id.log_in_forget_password);
        mPasswordCode = (EditText) findViewById(R.id.log_in_password_code);
        mVerificationCode = (EditText) findViewById(R.id.log_in_verification_code);

        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mUserAvatar.setOnClickListener(this);
        mForgetPassword.setOnClickListener(this);
        mPasswordCode.setOnClickListener(this);
        mVerificationCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.log_in_log_in_button:
                // do something
                finish();
                break;
            case R.id.log_in_log_up_button:
                startActivity(new Intent( LogInActivity.this, LogUpActivity.class ));
                break;
            case R.id.log_in_user_avatar:
                // 打开项目
                break;
            case R.id.log_in_forget_password:

                break;
            case R.id.log_in_password_code:

                break;
            case R.id.log_in_verification_code:

                break;
        }
    }

}

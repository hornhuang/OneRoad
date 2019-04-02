package com.example.oneroad.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneroad.R;
import com.example.oneroad.users.Guest;
import com.mob.MobSDK;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class LogUpActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     控件
      */
    private Button mLogUp, mGetVerificationCode;
    private FloatingActionButton mLogIn;
    private EditText mUserPhoneNumber, mUserVerificationCode,
        mUserPassword, mUserPasswordAgain;

    /*
     各种信息流
     */
    private Intent mFrom, mTo;
    private boolean mFromLogIn;
    private Guest guest;

    /*
     post 请求
     */
    private static final int POST = 2;
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /*
     Mod 短信服务集成
      */
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            Toasty.error(LogUpActivity.this, "错误，请检查网络！", Toast.LENGTH_SHORT, true).show();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            logIn();// 登陆
                            finish();
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            Toasty.error(LogUpActivity.this, "错误，请检查网络！", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        MobSDK.init(this);// Mod 开发平台短信服务使用权
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);

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

    /*
    注册控件点击事件
     */
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

    /*
    获得用户输入的信息
     */
    private boolean getEditsIfo(){
        String string[] = new String[]{
                mUserPhoneNumber.getText().toString(),
                mUserVerificationCode.getText().toString(),
                mUserPassword.getText().toString(),
                mUserPasswordAgain.getText().toString(),
        };
        if ( string[0].equals("") ){
            Toasty.info(this, "请输入手机号", Toast.LENGTH_SHORT, true).show();
            return false;
        } else if ( string[1].equals("") ){
            Toasty.info(this, "请输入验证码", Toast.LENGTH_SHORT, true).show();
            return false;
        } else if ( string[2].equals("") ){
            Toasty.info(this, "请输入密码", Toast.LENGTH_SHORT, true).show();
            return false;
        } else if ( string[3].equals("") ){
            Toasty.info(this, "请再次输入密码", Toast.LENGTH_SHORT, true).show();
            return false;
        } if ( !string[2].equals(string[3]) ){
            Toasty.info(this, "两次输入密码不相同", Toast.LENGTH_SHORT, true).show();
            return false;
        } else {
            return true;
        }
    }

    /*
     使用完EventHandler需注销，否则可能出现内存泄漏
      */
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    /** post 方法一：
     * （ 使用 okHttpUtils ）
     */
    private void logIn(){
        OkHttpUtils.post()//
                .url("http://47.107.132.227/api/mysql/getifo")
                .addParams("nickname","000")
                .addParams("phone"   ,mUserPhoneNumber.getText().toString())
                .addParams("password",mUserPassword.getText().toString())
                .addParams("avatar"  ,"000")
//                .headers(headers)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(LogUpActivity.this, "失败！请检查网络和输入格式 ！-->" + e.getMessage(), Toast.LENGTH_SHORT, true).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toasty.success(LogUpActivity.this, "欢迎加入我们！", Toast.LENGTH_SHORT, true).show();
                    }
                });
    }

    /*
    匹配点击事件
     */
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
                    String phoneNumber = mUserPhoneNumber.getText().toString();
                    String verificationCode = mUserVerificationCode.getText().toString();
//                     提交验证码，其中的code表示验证码，如“1357”
                    SMSSDK.submitVerificationCode("86", phoneNumber, verificationCode);
                }
                break;
            case R.id.log_up_get_verification_code:
                // do something
                String phoneNumber = mUserPhoneNumber.getText().toString();
                if ( !phoneNumber.equals("") ){
                    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                    SMSSDK.getVerificationCode("86", phoneNumber);
                }
                break;
            case R.id.log_up_user_avatar:
                // do something
                break;
        }
    }
}

package com.example.oneroad.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.oneroad.R;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_TIME_LENGH = 000;//设置闪屏时间三秒
    private Handler jumpHandler;//延迟执行，用于跳转
    private int SCREEN_WIDTH;//屏幕长宽
    private int SCREEN_HEIGHT;
    private int SHOW_SIZE_WIDTH = 0;
    private int SHOW_SIZE_HEIGHT = 0;

    private ImageView imageView;
    private LinearLayout linearLayout;

//    private Handler sizeHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 000){
//                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(SHOW_SIZE_WIDTH,SHOW_SIZE_HEIGHT));
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        imageView = (ImageView) findViewById(R.id.open_screen_image);
        linearLayout = (LinearLayout) findViewById(R.id.animation_screen);

        SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
        SCREEN_WIDTH = getWindowManager().getDefaultDisplay().getWidth();

//        openAnimation();
//        jumpNextActivity();
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }.start();

    }

//    private void openAnimation(){
//        new Thread(){
//            @Override
//            public void run() {
//                while (SHOW_SIZE_WIDTH <= SCREEN_WIDTH || SHOW_SIZE_HEIGHT <= SCREEN_HEIGHT){
//                    try {
//                        sleep(5);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (SHOW_SIZE_WIDTH <= SCREEN_WIDTH){
//                        SHOW_SIZE_WIDTH += 5;
//                    }
//                    if (SHOW_SIZE_HEIGHT <= SCREEN_HEIGHT){
//                        SHOW_SIZE_HEIGHT += 10;
//                    }
//                    Message msg = new Message();
//                    msg.what = 000;
//                    sizeHandler.sendMessage(msg);
//                }
//            }
//        }.start();
//    }

//    private void jumpNextActivity(){
//
//        jumpHandler = new Handler();
//        //延迟 SPLASH_DISPLAY_TIME_LENGH 时间后执行
//        jumpHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                SplashActivity.this.finish();
//            }
//        },SPLASH_DISPLAY_TIME_LENGH );
//
//    }

}

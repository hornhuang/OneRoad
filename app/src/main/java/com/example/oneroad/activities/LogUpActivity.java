package com.example.oneroad.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oneroad.R;

public class LogUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogUp;
    private FloatingActionButton mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);
        iniclick();
    }

    private void iniclick(){
        mLogIn = (FloatingActionButton) findViewById(R.id.log_up_log_in_button);
        mLogUp = (Button) findViewById(R.id.log_up_log_up_button);

        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.log_up_log_in_button:
                startActivity(new Intent( LogUpActivity.this, LogInActivity.class ));
                break;
            case R.id.log_up_log_up_button:
                // do something and finish
                finish();
                break;
            case R.id.log_up_set_nickname:
                // do something
                break;
            case R.id.log_up_user_avatar:
                // do something
                break;
        }
    }
}

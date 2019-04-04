package com.example.oneroad.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oneroad.R;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class PushEvaluationActivity extends AppCompatActivity {

    /*
    控件
     */
    private MaterialRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_elaluation);

        ratingBar = (MaterialRatingBar) findViewById(R.id.push_activity_rating_bar);
//        ratingBar.setNumStars(2);
    }



}

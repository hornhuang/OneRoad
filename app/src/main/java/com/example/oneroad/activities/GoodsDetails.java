package com.example.oneroad.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oneroad.R;
import com.flyco.tablayout.SlidingTabLayout;

public class GoodsDetails extends AppCompatActivity {

    /*
    控件
     */
    private SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        // 实例化控件
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.goods_details_tabs);
    }

    /*
    实例化控件
     */

}

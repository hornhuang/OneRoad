package com.example.oneroad.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.oneroad.R;
import com.example.oneroad.fragments.goodsdetails.GoodsDetailsFragment;
import com.example.oneroad.fragments.goodsdetails.GoodsEvaluationFragment;
import com.example.oneroad.fragments.goodsdetails.GoodsIntroduceFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class GoodsDetails extends BaseActivity {

    /*
    控件
     */
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        // 实例化控件
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.goods_details_tabs);
        viewPager = (ViewPager) findViewById(R.id.goods_details_viewpager);

        bindTabs();
    }

    /*
    为 tabs 绑定碎片
     */
    private void bindTabs(){
        final Fragment goodsDetailsFragment = new GoodsDetailsFragment();
        final Fragment goodsEvalationFragment = new GoodsEvaluationFragment();
        final Fragment goodsIntroduceFragment = new GoodsIntroduceFragment();
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(goodsIntroduceFragment);
        fragments.add(goodsDetailsFragment);
        fragments.add(goodsEvalationFragment);

        String[] strings = new String[]{"商品","详情","评论"};
        slidingTabLayout.setViewPager(viewPager, strings, GoodsDetails.this, fragments);
    }

}

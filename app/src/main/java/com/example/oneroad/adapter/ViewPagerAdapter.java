package com.example.oneroad.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.oneroad.fragments.NavMineFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private String[] tabTitleArray;
    private NavMineFragment navMineFragment;

    public ViewPagerAdapter(FragmentManager fm, NavMineFragment context,
                            List fragmentList, String[] tabTitleArray) {
        super(fm);
//        ...
        this.fragmentList = fragmentList;
        this.tabTitleArray = tabTitleArray;
        this.navMineFragment = context;
    }

    /* 重写与TabLayout配合 */

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleArray[position % tabTitleArray.length];
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }
}

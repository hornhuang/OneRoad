package com.example.oneroad.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.MenuItem;

import com.example.oneroad.R;
import com.example.oneroad.fragments.NavEncyclopediaFragment;
import com.example.oneroad.fragments.NavGoodsFragment;
import com.example.oneroad.fragments.NavMineFragment;
import com.example.oneroad.fragments.NavPrimePageFragment;
import com.example.oneroad.fragments.NavRouteFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_encyclopedia:
                    navEncyclopedia.run();
                    return true;
                case R.id.nav_goods:
                    navGoods.run();
                    return true;
                case R.id.nav_prime_page:
                    navPrimePage.run();
                    item.isChecked();
                    return true;
                case R.id.nav_route:
                    navRoute.run();
                    return true;
                case R.id.nav_mine:
                    navMine.run();
                    return true;
            }
            return false;
        }
    };

    private Runnable navEncyclopedia = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = new NavEncyclopediaFragment();
            FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
            transition.replace(R.id.main_fragment, fragment).commitAllowingStateLoss();
        }
    };

    private Runnable navGoods = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = new NavGoodsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, fragment).commitAllowingStateLoss();
        }
    };

    private Runnable navPrimePage = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = new NavPrimePageFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, fragment).commitAllowingStateLoss();
        }
    };

    private Runnable navRoute = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = new NavRouteFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, fragment).commitAllowingStateLoss();
        }
    };

    private Runnable navMine = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = new NavMineFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, fragment).commitAllowingStateLoss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bot_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.nav_prime_page);
    }

}
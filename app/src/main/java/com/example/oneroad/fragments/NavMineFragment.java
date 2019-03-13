package com.example.oneroad.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneroad.R;
import com.example.oneroad.activities.LogInActivity;
import com.example.oneroad.activities.LogUpActivity;
import com.example.oneroad.activities.MainActivity;
import com.example.oneroad.adapter.ViewPagerAdapter;
import com.example.oneroad.classes.NavMineCollection;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavMineFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mFragmentView;
    private ImageView mPopupMenu;
    private CircleImageView mUserImage;
    private Button mLogUp,mLogIn;
    private LinearLayout mCollect, mOrder, mRoutes,
        mMoreSets, mAboutUs;

    // TabLayout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if ( msg.what == 0x0 ){
                viewPager.getLayoutParams().height = msg.arg1;
            }
        }
    };
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] tabTitleArray = new String[]{
            "我发布的攻略",
            "我发布的百科"
    };

    private List<NavMineCollection> mList;

    public NavMineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView =  inflater.inflate(R.layout.fragment_nav_mine, container, false);
        iniClick();
        createTabLayout();
        return mFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // 绑定点击的监听事件
    private void iniClick(){
        mUserImage = (CircleImageView) mFragmentView.findViewById(R.id.nav_mine_user_image) ;
        mLogUp = (Button) mFragmentView.findViewById(R.id.nav_mine_sigh_in);
        mLogIn = (Button) mFragmentView.findViewById(R.id.nav_mine_sigh_up);
        mCollect = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_collect);
        mOrder = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_order);
        mRoutes = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_routes);
//        mStrategy = (TabItem) mFragmentView.findViewById(R.id.nav_mine_strategy);
//        mCyclopedia = (TabItem) mFragmentView.findViewById(R.id.nav_mine_cyclopedia);
        viewPager = (ViewPager) mFragmentView.findViewById(R.id.nav_mine_view_paper);
        mPopupMenu = (ImageView) mFragmentView.findViewById(R.id.tv_popup_menu);
        mUserImage.setOnClickListener(this);
        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mRoutes.setOnClickListener(this);
//        mStrategy.setOnClickListener(this);
//        mCyclopedia.setOnClickListener(this);
        mPopupMenu.setOnClickListener(this);

        mUserImage.getLayoutParams().width = mUserImage.getLayoutParams().height;// 设置头像大小
        mUserImage.setImageResource(R.drawable.ima);
    }

    private void createTabLayout(){
        tabLayout = (TabLayout) mFragmentView.findViewById(R.id.nav_mine_tab_layout);
//        tabLayout.setTabMode (TabLayout.MODE_SCROLLABLE) //设置平铺
        NavMineStrategyFragment navMineStrategyFragment = new NavMineStrategyFragment();
        NavMineCyclopediaFragment navMineCyclopediaFragment = new NavMineCyclopediaFragment();
        fragmentList.add(navMineStrategyFragment);
        fragmentList.add(navMineCyclopediaFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getActivity().getSupportFragmentManager(), this, fragmentList, tabTitleArray);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_mine_user_image://用户头像
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_mine_sigh_up://注册
                startActivity(new Intent( getActivity(), LogUpActivity.class));
                break;
            case R.id.nav_mine_sigh_in://登入
                startActivity(new Intent( getActivity(), LogInActivity.class));
                break;
            case R.id.nav_mine_collect://收藏
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_mine_order://订单
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_mine_routes://路线
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.tv_popup_menu:// 弹出菜单
                showPopupMenu();

                break;
        }

    }

    private void showPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(getActivity(),mPopupMenu);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_mine_more_sets:
                        Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_mine_about_us:
                        Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        //do nothing
                }

                return false;
            }
        });
        popupMenu.show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

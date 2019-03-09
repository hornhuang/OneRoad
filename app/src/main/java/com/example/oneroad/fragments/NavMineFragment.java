package com.example.oneroad.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.oneroad.R;
import com.example.oneroad.classes.NavGoodsGoods;
import com.example.oneroad.classes.NavMineCollection;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

public class NavMineFragment extends Fragment implements View.OnClickListener,PictureForRecyclerView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mFragmentView;
    private Button mLogUp,mLogIn;
    private LinearLayout mCollect, mOrder, mRoutes, mStrategy, mCyclopedia,
        mMoreSets, mAboutUs;

    //我的收藏 RecyclerView
    private RecyclerView mRecyclerView;
    private List<NavMineCollection> mList;

    public NavMineFragment() {
        // Required empty public constructor
    }

    public static NavMineFragment newInstance(String param1, String param2) {
        NavMineFragment fragment = new NavMineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        mLogUp = (Button) mFragmentView.findViewById(R.id.nav_mine_sigh_in);
        mLogIn = (Button) mFragmentView.findViewById(R.id.nav_mine_sigh_up);
        mCollect = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_collect);
        mOrder = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_order);
        mRoutes = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_routes);
        mStrategy = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_strategy);
        mCyclopedia = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_cyclopedia);
        mMoreSets = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_more_sets);
        mAboutUs = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_about_us);;
        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mRoutes.setOnClickListener(this);
        mStrategy.setOnClickListener(this);
        mCyclopedia.setOnClickListener(this);
        mMoreSets.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_mine_user_image://用户头像
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_mine_sigh_up://注册
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_mine_sigh_in://登入
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

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
            case R.id.nav_mine_strategy://攻略
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_mine_cyclopedia://百科
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_mine_recycler_view:
                // do nothing ...
                break;
            case R.id.nav_mine_more_sets://设置
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_mine_about_us://我们
                Toast.makeText(getActivity(),"you click!",Toast.LENGTH_LONG).show();

                break;
        }
    }

    @Override
    public void downloadPicture() {
        String url = "http://47.107.132.227/form";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)//连接超时
                .readTimeOut(20000)//读取超时
                .writeTimeOut(20000)//写超时
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        // show error message to users
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        // add bitmap to list here
                    }
                });
    }

    @Override
    public void getImage() {

    }

    @Override
    public void iniGoodsRecyclerView() {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

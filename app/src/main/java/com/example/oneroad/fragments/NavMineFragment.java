package com.example.oneroad.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.oneroad.recycleradapter.NavMineAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

public class NavMineFragment extends Fragment implements View.OnClickListener,PictureForRecyclerView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mFragmentView;
    private CircleImageView mUserImage;
    private Button mLogUp,mLogIn;
    private LinearLayout mCollect, mOrder, mRoutes, mStrategy, mCyclopedia,
        mMoreSets, mAboutUs;

    //我的收藏 RecyclerView
    private RecyclerView mRecyclerView;
    private List<NavMineCollection> mList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if ( msg.what == 0x0 ){
                iniGoodsRecyclerView();
            }
        }
    };

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
        getImage();
        iniClick();
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
        mStrategy = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_strategy);
        mCyclopedia = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_cyclopedia);
        mMoreSets = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_more_sets);
        mAboutUs = (LinearLayout) mFragmentView.findViewById(R.id.nav_mine_about_us);
        mUserImage.setOnClickListener(this);
        mLogUp.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mRoutes.setOnClickListener(this);
        mStrategy.setOnClickListener(this);
        mCyclopedia.setOnClickListener(this);
        mMoreSets.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);

        mUserImage.getLayoutParams().width = mUserImage.getLayoutParams().height;// 设置头像大小
        mUserImage.setImageResource(R.drawable.ima);
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
                        mList.add(new NavMineCollection(bitmap));
                    }
                });
    }

    @Override
    public void getImage() {
        mList = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                int i = 0 ;
                while ( i++ < 10 ){
                    try {
                        downloadPicture();
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = new Message();
                message.what = 0x0;
                handler.sendMessage(message);
            }
        }.start();
    }

    @Override
    public void iniGoodsRecyclerView() {
        List<NavMineCollection> mData = mList;
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.nav_mine_recycler_view);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);// 设置本身可以移动
        mRecyclerView.setAdapter(new NavMineAdapter(this, mData));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

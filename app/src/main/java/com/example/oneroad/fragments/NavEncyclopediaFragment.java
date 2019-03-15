package com.example.oneroad.fragments;

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

import com.example.oneroad.R;
import com.example.oneroad.goods.EncyclopediaListGoods;
import com.example.oneroad.adapter.EncyclopediaAdapter;
import com.example.oneroad.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class NavEncyclopediaFragment extends Fragment implements PictureForRecyclerView{

    private View view;
    private RecyclerView mRecyclerView;
    private List<EncyclopediaListGoods> mGoodsRecyclerList;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0){
                iniGoodsRecyclerView();
            }
        }
    };

    public NavEncyclopediaFragment() {
        // Required empty public constructor
    }


    public static NavEncyclopediaFragment newInstance(String param1, String param2) {
        NavEncyclopediaFragment fragment = new NavEncyclopediaFragment();
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
        view =  inflater.inflate(R.layout.fragment_nav_encyclopedia, container, false);
        setBanner(view);
        downloadPicture();
        return view;
    }


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setBanner(View view){
        //配置 images 和 titles
        for (int i = 0 ; i < 4 ; i++){
            images.add("http://47.107.132.227/form");
            titles.add("这里是测试用例");
        }

        Banner banner = (Banner) view.findViewById(R.id.nav_encyclopedia_banner);
        banner.setImages(images).setImageLoader(new GlideImageLoader());

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void downloadPicture() {
        mGoodsRecyclerList = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                while (mGoodsRecyclerList.size() < 15){
                    try {
                        sleep(100);
                        getImage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                msg.what = 0x0;
                handler.sendMessage(msg);
            }
        }.start();
    }

    //
    @Override
    public void getImage()
    {
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
                        mGoodsRecyclerList.add(new EncyclopediaListGoods(bitmap));
                    }
                });
    }

    @Override
    public void iniGoodsRecyclerView() {
        List<EncyclopediaListGoods> mData = mGoodsRecyclerList;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.nav_encyclopedia_recycler_view);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.getLayoutParams().height = mData.size() * 300;
        mRecyclerView.setAdapter(new EncyclopediaAdapter(mData, this));
    }

}

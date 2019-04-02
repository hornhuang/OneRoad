package com.example.oneroad.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oneroad.R;
import com.example.oneroad.activities.GoodsDetails;
import com.example.oneroad.goods.NavGoodsGoods;
import com.example.oneroad.adapter.NavGoodsAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class NavGoodsFragment extends Fragment implements PictureForRecyclerView{

    private View mGoodsView;
    private List<NavGoodsGoods> mList = new ArrayList<>();
    private RecyclerView mRecyclerView ;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0){
                iniGoodsRecyclerView();
            }
        }
    };

    public NavGoodsFragment() {
        // Required empty public constructor
    }

    public static NavGoodsFragment newInstance(String param1, String param2) {
        NavGoodsFragment fragment = new NavGoodsFragment();
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
        mGoodsView =  inflater.inflate(R.layout.fragment_nav_goods, container, false);
        getImage();
        startActivity(new Intent(getActivity(), GoodsDetails.class));
        return mGoodsView;
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
        void onFragmentInteraction(Uri uri);
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
                        mList.add(new NavGoodsGoods(bitmap));
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
                while ( i++ < 6 ){
                    try {
                        downloadPicture();
                        sleep(200);
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

    @Override
    public void iniGoodsRecyclerView() {
        List<NavGoodsGoods> mData = mList;
        mRecyclerView = (RecyclerView) mGoodsView.findViewById(R.id.nav_goods_recycler);
//        mRecyclerView.getLayoutParams().height = mList.size() * 300;
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(new NavGoodsAdapter(mData, this));
    }

}

package com.example.oneroad.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.oneroad.R;
import com.example.oneroad.activities.MainActivity;
import com.example.oneroad.adapter.NavMineAdapter;
import com.example.oneroad.classes.NavMineCollection;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class NavMineCyclopediaFragment extends Fragment implements PictureForRecyclerView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mFragment;

    private List<NavMineCollection> mList;
    private RecyclerView mRecyclerView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            iniGoodsRecyclerView();
        }
    };

    public NavMineCyclopediaFragment() {
        // Required empty public constructor
    }

    public static NavMineCyclopediaFragment newInstance(String param1, String param2) {
        NavMineCyclopediaFragment fragment = new NavMineCyclopediaFragment();
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
        mFragment = inflater.inflate(R.layout.fragment_nav_mine_cyclopedia, container, false);
        getImage();
        return mFragment;
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
                while ( mList.size() < 10 ){
                    try {
                        downloadPicture();
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                msg.what = 0x0;
                handler.sendMessage( msg );
            }
        }.start();
    }

    @Override
    public void iniGoodsRecyclerView() {
        List<NavMineCollection> mData = mList;
        mRecyclerView = (RecyclerView) mFragment.findViewById(R.id.nav_mine_recycler_view);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2) ;
        mRecyclerView.setLayoutManager( manager );
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.getLayoutParams().height = 300 * mData.size();
        mRecyclerView.setAdapter(new NavMineAdapter( this, mData ));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

package com.example.oneroad.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oneroad.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class NavRouteFragment extends Fragment implements PictureForRecyclerView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //本碎片界面
    private View view;
    private String flag = "species";
    private Bitmap mBitmap;
    private List<ImageView> mSpeciesImageViews = new ArrayList<>();
    private List<ImageView> mScenicImageViews = new ArrayList<>();
    private List<ImageView> mRoutesImageViews = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            if (flag.equals("species")){
                for ( ImageView imageViewSpecies : mSpeciesImageViews )
                    imageViewSpecies.setImageBitmap(mBitmap);
//            }else if (flag.equals("scenic")){
                for ( ImageView imageViewScenic : mScenicImageViews)
                    imageViewScenic.setImageBitmap(mBitmap);
//            }else if (flag.equals("routes")){
                for ( ImageView imageViewRoutes : mRoutesImageViews)
                    imageViewRoutes.setImageBitmap(mBitmap);
//            }
        }
    };

    public NavRouteFragment() {
        // Required empty public constructor
    }

    public static NavRouteFragment newInstance(String param1, String param2) {
        NavRouteFragment fragment = new NavRouteFragment();
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
        view = inflater.inflate(R.layout.fragment_nav_route, container, false);
        iniGoodsRecyclerView();
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
                        mBitmap = bitmap;
                        Message msg = new Message();
                        handler.sendMessage(msg);
                    }
                });
    }

    @Override
    public void getImage() {
        new Thread(){
            @Override
            public void run() {
                flag = "species";
                downloadPicture();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                flag = "scenic";
                downloadPicture();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                flag = "routes";
                downloadPicture();
            }
        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                while (true){
//                    flag
//                    downloadPicture();
//                }
//            }
//        }.start();
    }

    @Override
    public void iniGoodsRecyclerView() {
        mSpeciesImageViews.add((ImageView) view.findViewById(R.id.nav_route_species_image_1));
        mSpeciesImageViews.add((ImageView) view.findViewById(R.id.nav_route_species_image_2));
        mSpeciesImageViews.add((ImageView) view.findViewById(R.id.nav_route_species_image_3));
        mScenicImageViews.add((ImageView) view.findViewById(R.id.nav_route_scenic_image_1));
        mScenicImageViews.add((ImageView) view.findViewById(R.id.nav_route_scenic_image_2));
        mScenicImageViews.add((ImageView) view.findViewById(R.id.nav_route_scenic_image_3));
        mRoutesImageViews.add((ImageView) view.findViewById(R.id.nav_route_routes_image_1_1));
        mRoutesImageViews.add((ImageView) view.findViewById(R.id.nav_route_routes_image_1_2));
        mRoutesImageViews.add((ImageView) view.findViewById(R.id.nav_route_routes_image_2_1));
        mRoutesImageViews.add((ImageView) view.findViewById(R.id.nav_route_routes_image_2_2));
        getImage();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

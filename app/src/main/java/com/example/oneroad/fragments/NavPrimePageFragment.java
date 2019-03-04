package com.example.oneroad.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneroad.R;
import com.example.oneroad.classes.PrimePageGoods;
import com.example.oneroad.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class NavPrimePageFragment extends Fragment {

    private RecyclerView mHorizontalListView;//水平商品轮播图
    private RecyclerView mVerticalListView;//竖直商品轮播图
    private Button addMoreButton;//点击增加更多
    private List<PrimePageGoods> mainVerticalList;//竖直商品，点击增加10项
    private View primeView;//本界面布局

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> images = new ArrayList<>();

    private List<String> titles = new ArrayList<>();

    public NavPrimePageFragment() {
        // Required empty public constructor
    }

    public static NavPrimePageFragment newInstance(String param1, String param2) {
        NavPrimePageFragment fragment = new NavPrimePageFragment();
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
        View view = inflater.inflate(R.layout.fragment_nav_prime_page, container, false);
        setBanner(view);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void setBanner(View view){
        //配置 images 和 titles
        for (int i = 0 ; i < 4 ; i++){
            images.add("http://47.107.132.227/form");
            titles.add("这里是测试用例");
        }

        Banner banner = (Banner) view.findViewById(R.id.banner);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
//        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        banner.setImages(images);
//        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
//        //设置轮播时间
//        banner.setDelayTime(1500);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();
    }



//    //与水平相同设置竖直
//    public void setmVerticalListView() {
//        List<PrimePageGoods> data = mainVerticalList;
//        mVerticalListView = (RecyclerView) primeView.findViewById(R.id.main_prime_page_vertical_list);
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(),1);
//        mVerticalListView.setLayoutManager(manager);
//        mVerticalListView.setHasFixedSize(true);//设置recycler不可滑动
//        mVerticalListView.setNestedScrollingEnabled(false);
//        mVerticalListView.getLayoutParams().height = 250 * data.size() ;
//        mVerticalListView.setAdapter(new VerticalListAdapter(data));
//    }
//
//    class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.VH>{
//        //② 创建ViewHolder
//        class VH extends RecyclerView.ViewHolder{
//            private final TextView name,introduce,place,price;
//            private final ImageView image;
//            public VH(View v) {
//                super(v);
//                v.getLayoutParams().height = 250 ;
//                image = (ImageView) v.findViewById(R.id.main_prime_vertical_list_view_image);
//                name = (TextView) v.findViewById(R.id.main_prime_vertical_list_view_name);
//                introduce = (TextView) v.findViewById(R.id.main_prime_vertical_list_view_introduce);
//                place = (TextView) v.findViewById(R.id.main_prime_vertical_list_view_place);
//                price = (TextView) v.findViewById(R.id.main_prime_vertical_list_view_price);
//            }
//        }
//
//        private List<PrimePageGoods> mDatas;
//        public VerticalListAdapter(List<PrimePageGoods> data) {
//            this.mDatas = data;
//        }
//
//        //③ 在Adapter中实现3个方法
//        @Override
//        public void onBindViewHolder(VH holder, int position) {
//            holder.name.setText(mDatas.get(position).getName());
//            holder.introduce.setText(mDatas.get(position).getName());
//            holder.place.setText(mDatas.get(position).getPickedPlace());
//            holder.price.setText(mDatas.get(position).getPickedDate());
//            holder.image.setImageBitmap(mDatas.get(position).getBitmapA());
//            final int mListPosition = position;
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //item 点击事件
////                    Log.v("123123",this + "---  --->" + mDatas.get(mListPosition).getId());
////                    startActivity(DetailActivity.newIntent(getActivity(),"我捡的宝贝",mDatas.get(mListPosition).getId()));
//                    PrimePageGoods lostItem = mDatas.get(mListPosition);
//                    Intent intent = new Intent(getActivity(),MyLostDetailActivity.class);
//                    Bundle bundle = new Bundle();
////                    private LostItem ;
////                    private ImageView ;
////                    private ImageView ;
////                    private TextView ;
////                    private TextView ;
////                    private TextView ;
////                    private TextView ;
////                    private TextView ;
////                    private TextView ;
////                    private TextView ;
////                    bundle.putSerializable("mImageA", lostItem.getBitmapA());
////                    bundle.putSerializable("mImageB", lostItem.getBitmapB());
//                    bundle.putSerializable("mName", lostItem.getName());
//                    bundle.putSerializable("mPlace", lostItem.getPickedPlace());
//                    bundle.putSerializable("mFounder", lostItem.getFounder());
//                    bundle.putSerializable("mDate", lostItem.getPickedDate());
//                    bundle.putSerializable("mTel", lostItem.getTel());
//                    bundle.putSerializable("mQQ", lostItem.getQQ());
//                    bundle.putSerializable("mWeChat", lostItem.getWeChat());
//                    bundle.putSerializable("bitmapA", lostItem.getImageA().getUrl());
//                    bundle.putSerializable("bitmapB", lostItem.getImageB().getUrl());
//
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDatas.size();
//        }
//
//        @Override
//        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//            //LayoutInflater.from指定写法
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_lost_property_list_view, parent, false);
//            return new VH(v);
//        }
//    }
//
//    private void setBitmap(final PrimePageGoods lostItem){
//        new Thread(){
//            @Override
//            public void run() {
//                lostItem.setBitmapA(getPicture(lostItem.getImageA().getUrl()));
//                lostItem.setBitmapB(getPicture(lostItem.getImageB().getUrl()));
//                mainVerticalList.add(lostItem);
//            }
//        }.start();
//    }
//
//    private void initVerticalData(){
//        Person user = BmobUser.getCurrentUser(Person.class);//先从云端读入数据
//        query = new BmobQuery<>();
//        query.addWhereEqualTo("mPerson", user);
//        mainVerticalList = new ArrayList<>();
//        query.findObjects(new FindListener<LostItem>() {
//            @Override
//            public void done(List<PrimePageGoods> object, BmobException e) {
//                if(e==null){
//                    for (int i = 0 ; i < object.size() ; i++){
//                        setBitmap(object.get(i));
//                    }
//                }else{
//                    Log.v("123456",e.toString() + "---2---" + e.getErrorCode());
//                    Toast.makeText(getActivity(), "失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        new Thread(){
//            @Override
//            public void run() {
//                while (mainVerticalList.size() == length){
//                    try {
//                        sleep(200);
//                    }catch (Exception e){
//                        Toast.makeText(getActivity(),"信息获取失败 T.T ",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                try {
//                    sleep(300);
//                    Message msg = new Message();
//                    msg.what = 0x0;
//                    handler.sendMessage(msg);
//                    length = mainVerticalList.size();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    private void addMore(){
//        addMoreButton = (Button) primeView.findViewById(R.id.main_prime_page_add_more);
//        LostItem mNewLostOne = new LostItem();
//        addMoreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mAddTimes == 0){
//                    initVerticalData();
//                }else if(mAddTimes == 3){
//                    Toast.makeText(getActivity(), "不要老戳宝宝 宝宝痛痛！",Toast.LENGTH_SHORT).show();
//                    addMoreButton.setClickable(false);
//                }
//                mAddTimes ++;
//                new Thread(){
//                    @Override
//                    public void run() {
//                        try {
//                            sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        mAddTimes = 0;
//                        addMoreButton.setClickable(true);
//                    }
//                }.start();
//            }
//        });
//    }
//
//    public Bitmap getPicture(String path){
//        Bitmap bm = null;
//        try{
//            URL url = new URL(path);
//            URLConnection connection=url.openConnection();
//            connection.connect();
//            InputStream inputStream=connection.getInputStream();
//            bm= BitmapFactory.decodeStream(inputStream);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bm;
//    }
}
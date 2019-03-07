package com.example.oneroad.utils;

import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oneroad.R;
import com.example.oneroad.activities.BaseActivity;
import com.example.oneroad.classes.EncyclopediaListGoods;
import com.example.oneroad.fragments.PictureForRecyclerView;
import com.example.oneroad.recycleradapter.EncyclopediaAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class BitmapDownLoad extends BaseActivity{

    public static Bitmap getImage(BaseActivity activity)
    {
        final Bitmap[] mBitmap = new Bitmap[1];
        String url = "http://47.107.132.227/form";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(activity)//
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
                         mBitmap[0] = bitmap;
                    }
                });
        return mBitmap[0];
    }


}

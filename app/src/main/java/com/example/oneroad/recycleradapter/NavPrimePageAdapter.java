package com.example.oneroad.recycleradapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oneroad.R;
import com.example.oneroad.activities.PrimePageGoodsActivity;
import com.example.oneroad.classes.PrimePageListGoods;
import com.example.oneroad.fragments.NavPrimePageFragment;

import java.util.List;

public class NavPrimePageAdapter extends RecyclerView.Adapter<VH> {
    //传入列表元素集合
    private List<PrimePageListGoods> mDatas;
    private NavPrimePageFragment navPrimePageFragment;

    public NavPrimePageAdapter(List<PrimePageListGoods> data, NavPrimePageFragment navPrimePageFragment){
        this.mDatas = data;
        this.navPrimePageFragment = navPrimePageFragment;
    }

    // 定义 ViewHolder 类，对象内容的可视化
    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.imageid.setImageBitmap(mDatas.get(i).getGoodsImage());
        vh.imageid.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                navPrimePageFragment.startActivity(new Intent(navPrimePageFragment.getActivity(), PrimePageGoodsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prime_page_list_item, viewGroup, false);
        return new VH(view);
    }
}

//连接上布局文件
class VH extends RecyclerView.ViewHolder{

    public final ImageView imageid;

    public VH(View view){
        super(view);
        view.getLayoutParams().height = 300 ;//获得布局参数的高度，并设置
        imageid = (ImageView) view.findViewById(R.id.nav_prime_page_list_item);
    }

}

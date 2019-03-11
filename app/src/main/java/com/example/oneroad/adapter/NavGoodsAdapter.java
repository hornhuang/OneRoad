package com.example.oneroad.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oneroad.R;
import com.example.oneroad.activities.PrimePageGoodsActivity;
import com.example.oneroad.classes.NavGoodsGoods;
import com.example.oneroad.fragments.NavGoodsFragment;

import java.util.ArrayList;
import java.util.List;

public class NavGoodsAdapter extends RecyclerView.Adapter<NavGoodsAdapter.VH> {

    private List<NavGoodsGoods> mData = new ArrayList<>();
    private NavGoodsFragment navGoodsFragment = new NavGoodsFragment();

    public NavGoodsAdapter(List<NavGoodsGoods> mData, NavGoodsFragment navGoodsFragment) {
        this.mData = mData;
        this.navGoodsFragment = navGoodsFragment;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.imageView.setImageBitmap(mData.get(i).getBitmap());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
                navGoodsFragment.startActivity(new Intent(navGoodsFragment.getActivity(), PrimePageGoodsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_goods_item, viewGroup, false));
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            itemView.getLayoutParams().width = navGoodsFragment.getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2;
            imageView = (ImageView) itemView.findViewById(R.id.nav_goods_goods_item);
        }
    }

}
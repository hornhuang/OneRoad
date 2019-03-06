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
import com.example.oneroad.classes.EncyclopediaListGoods;
import com.example.oneroad.fragments.NavEncyclopediaFragment;

import java.util.List;

public class EncyclopediaAdapter extends RecyclerView.Adapter<EncyclopediaAdapter.VH> {

    private List<EncyclopediaListGoods> mData;
    private NavEncyclopediaFragment navEncyclopediaFragment;

    public EncyclopediaAdapter(List<EncyclopediaListGoods> mData, NavEncyclopediaFragment navEncyclopediaFragment) {
        this.mData = mData;
        this.navEncyclopediaFragment = navEncyclopediaFragment;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.imageView.setImageBitmap(mData.get(i).getGoodsImage());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... do something
                navEncyclopediaFragment.startActivity(new Intent(navEncyclopediaFragment.getActivity(), PrimePageGoodsActivity.class));
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prime_page_list_item, viewGroup, false);
        return new VH(view);
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public VH(View view){
            super(view);
            view.getLayoutParams().height = 300;
            imageView = (ImageView) view.findViewById(R.id.nav_prime_page_list_item);
        }
    }
}
package com.example.oneroad.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.oneroad.R;
import com.example.oneroad.classes.NavMineCollection;
import com.example.oneroad.fragments.NavMineCyclopediaFragment;
import com.example.oneroad.fragments.NavMineFragment;
import com.example.oneroad.fragments.NavMineStrategyFragment;

import java.util.List;

public class NavMineAdapter extends RecyclerView.Adapter<NavMineAdapter.VH> {

    private NavMineCyclopediaFragment mineCyclopediaFragment;
    private NavMineStrategyFragment mineStrategyFragment;
    private List<NavMineCollection> mList;

    public NavMineAdapter(NavMineStrategyFragment mMineFragment, List<NavMineCollection> mList) {
        this.mineStrategyFragment = mMineFragment;
        this.mList = mList;
    }

    public NavMineAdapter(NavMineCyclopediaFragment mineCyclopediaFragment, List<NavMineCollection> mList) {
        this.mineCyclopediaFragment = mineCyclopediaFragment;
        this.mList = mList;
    }

    public NavMineAdapter(NavMineStrategyFragment mMineFragment) {
        this.mineStrategyFragment = mMineFragment;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prime_page_list_item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.imageView.setImageBitmap(mList.get(i).getmCollectImage());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            itemView.getLayoutParams().height = 300;
            imageView = itemView.findViewById(R.id.nav_prime_page_list_item);
        }
    }

}

package com.example.oneroad.recycleradapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BaseAdapter implements BaseViewHolder {


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder.VH vh, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public BaseViewHolder.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}

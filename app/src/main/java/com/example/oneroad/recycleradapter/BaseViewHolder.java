package com.example.oneroad.recycleradapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public interface BaseViewHolder {

    void onBindViewHolder(@NonNull VH vh, int i);

    int getItemCount();

    VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }

}

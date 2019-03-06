package com.example.oneroad.fragments;

public interface PictureForRecyclerView {
    // 线程下载图片
    void downloadPicture();

    // 使用 okHttp 下载图片
    void getImage();

    // 根据 List 生成 RecyclerView
    void iniGoodsRecyclerView();

}

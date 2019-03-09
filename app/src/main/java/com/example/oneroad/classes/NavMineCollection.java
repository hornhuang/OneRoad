package com.example.oneroad.classes;

import android.graphics.Bitmap;

public class NavMineCollection {

    private int imageId;
    private Bitmap goodsImage;

    public NavMineCollection(Bitmap goodsImage) {
        this.imageId = imageId;
        this.goodsImage = goodsImage;
    }

    public NavMineCollection(int imageId, Bitmap goodsImage) {
        this.imageId = imageId;
        this.goodsImage = goodsImage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Bitmap getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(Bitmap goodsImage) {
        this.goodsImage = goodsImage;
    }


}

package com.example.oneroad.classes;

import android.graphics.Bitmap;

public class PrimePageListGoods{
    private int imageId;
    private Bitmap goodsImage;

    public PrimePageListGoods(Bitmap goodsImage) {
        this.imageId = imageId;
        this.goodsImage = goodsImage;
    }

    public PrimePageListGoods(int imageId, Bitmap goodsImage) {
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
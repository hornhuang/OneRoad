package com.example.oneroad.goods;

import android.graphics.Bitmap;

public class EncyclopediaListGoods {
    private int imageId;
    private Bitmap goodsImage;

    public EncyclopediaListGoods(Bitmap goodsImage) {
        this.imageId = imageId;
        this.goodsImage = goodsImage;
    }

    public EncyclopediaListGoods(int imageId, Bitmap goodsImage) {
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

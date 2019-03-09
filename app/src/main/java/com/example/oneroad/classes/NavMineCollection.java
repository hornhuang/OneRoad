package com.example.oneroad.classes;

import android.graphics.Bitmap;

public class NavMineCollection {

    private int imageId;
    private Bitmap mCollectImage;

    public NavMineCollection(Bitmap goodsImage) {
        this.imageId = imageId;
        this.mCollectImage = goodsImage;
    }

    public NavMineCollection(int imageId, Bitmap goodsImage) {
        this.imageId = imageId;
        this.mCollectImage = goodsImage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Bitmap getmCollectImage() {
        return mCollectImage;
    }

    public void setCollectImage(Bitmap goodsImage) {
        this.mCollectImage = goodsImage;
    }


}

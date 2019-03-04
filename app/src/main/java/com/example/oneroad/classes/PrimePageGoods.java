package com.example.oneroad.classes;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

public class PrimePageGoods {
    private int name;
    private int ifo;
    private File file;
    private Bitmap bitmap;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getIfo() {
        return ifo;
    }

    public void setIfo(int ifo) {
        this.ifo = ifo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

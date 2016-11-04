package com.example.hante.newprojectsum.okhttpactivity.model;

import java.io.Serializable;

/**
 *  Zhihu
 */

public class ZhiHuInfo implements Serializable{
     private String mId;
     private String mImg;
     private String mTitle;

    public ZhiHuInfo (String mId, String mImg, String mTitle) {
        this.mId = mId;
        this.mImg = mImg;
        this.mTitle = mTitle;
    }

    public ZhiHuInfo () {
    }

    public String getmId () {
        return mId;
    }

    public void setmId (String mId) {
        this.mId = mId;
    }

    public String getmImg () {
        return mImg;
    }

    public void setmImg (String mImg) {
        this.mImg = mImg;
    }

    public String getmTitle () {
        return mTitle;
    }

    public void setmTitle (String mTitle) {
        this.mTitle = mTitle;
    }
}

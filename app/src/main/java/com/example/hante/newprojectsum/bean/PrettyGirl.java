package com.example.hante.newprojectsum.bean;

import java.io.Serializable;

/**
 * Created by handan on 2016/10/14.
 */

public class PrettyGirl implements Serializable {

    public String mWho;
    public String mImg;
    public String mPublished;

    public String getmWho () {
        return mWho;
    }

    public void setmWho (String mWho) {
        this.mWho = mWho;
    }

    public String getmImg () {
        return mImg;
    }

    public void setmImg (String mImg) {
        this.mImg = mImg;
    }

    public String getmPublished () {
        return mPublished;
    }

    public void setmPublished (String mPublished) {
        this.mPublished = mPublished;
    }

    public PrettyGirl () {
    }

    public PrettyGirl (String mWho, String mImg, String mPublished) {
        this.mWho = mWho;
        this.mImg = mImg;
        this.mPublished = mPublished;
    }
}

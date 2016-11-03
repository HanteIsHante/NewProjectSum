package com.example.hante.newprojectsum.meterialdesign.bean;

import java.io.Serializable;

/**
 * Zhihu
 */

public class ZhihuArt implements Serializable {

    private String mId;
    private String mDate;
    private String mImage;
    private String mGa_prefix;
    private String mTitle;

    public String getmId () {
        return mId;
    }

    public void setmId (String mId) {
        this.mId = mId;
    }

    public String getmDate () {
        return mDate;
    }

    public void setmDate (String mDate) {
        this.mDate = mDate;
    }

    public String getmImage () {
        return mImage;
    }

    public void setmImage (String mImage) {
        this.mImage = mImage;
    }

    public String getmGa_prefix () {
        return mGa_prefix;
    }

    public void setmGa_prefix (String mGa_prefix) {
        this.mGa_prefix = mGa_prefix;
    }

    public String getmTitle () {
        return mTitle;
    }

    public void setmTitle (String mTitle) {
        this.mTitle = mTitle;
    }
}

package com.example.hante.newprojectsum.bean;

import java.io.Serializable;

/**
 *  闹钟提醒
 */

public class RemindList implements Serializable{
    public String mTitle;
    public String mContent;
    public Long mData;

    public RemindList (String mTitle, String mContent, Long mData) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mData = mData;
    }

    public RemindList () {
    }
    public String getTitle () {
        return mTitle;
    }

    public void setTitle (String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent () {
        return mContent;
    }

    public void setContent (String mContent) {
        this.mContent = mContent;
    }

    public Long getData () {
        return mData;
    }

    public void setData (Long mData) {
        this.mData = mData;
    }
}

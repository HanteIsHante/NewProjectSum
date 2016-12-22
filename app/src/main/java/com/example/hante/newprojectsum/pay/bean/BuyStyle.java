package com.example.hante.newprojectsum.pay.bean;

import java.io.Serializable;

/**
 * 购买方式封装
 */

public class BuyStyle implements Serializable{

    private int id_code;
    private int rate;

    public int getId_code () {
        return id_code;
    }

    public void setId_code (int id_code) {
        this.id_code = id_code;
    }

    public int getRate () {
        return rate;
    }

    public void setRate (int rate) {
        this.rate = rate;
    }
}

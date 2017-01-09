package com.example.hante.newprojectsum.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * 通过 ORMLitem 构造数据库
 * http://ocnyang.com/2016/08/12/OrmLiteAbout/#more
 *  首先在User类上添加@DatabaseTable(tableName = “tb_user”)，
 *  标明这是数据库中的一张表，标明为tb_user
 *  然后分别在属性上添加@DatabaseField(columnName = “name”) ，columnName的值为该字段在数据中的列名
 *  @DatabaseField(generatedId = true) ，generatedId 表示id为主键且自动生成
 */
@DatabaseTable(tableName = "tb_user")
public class User implements Serializable{

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private int name;
    @DatabaseField(columnName = "phone")
    private int phone;

    public User () {
    }

    public User (int name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getName () {
        return name;
    }

    public void setName (int name) {
        this.name = name;
    }

    public int getPhone () {
        return phone;
    }

    public void setPhone (int phone) {
        this.phone = phone;
    }
}

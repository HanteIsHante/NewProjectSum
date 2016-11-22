package com.example.hante.newprojectsum.sqlite;

import android.provider.BaseColumns;

/**
 * 数据库表 字段
 */

public final class DBEntry {

    public DBEntry () {
    }

    /**
     * User 用户表
     */
    public static abstract class UserTable implements BaseColumns{
            public static final String TABLE_NAME = "userInfo";
            public static final String USER_NAME = "userName";
            public static final String USER_PASSWORD ="userPassword";
            public static final String USER_EMAIL = "userEmail";
    }

    /**
     * 提醒事件 表
     */
    public  static abstract class RemindTable implements BaseColumns{
            public static final String TABLE_NAME = "Remind_data";
            public static final String REMIND_TITLE = "remind_title";
            public static final String REMIND_CONTENT = "remind_content";
            public static final String REMIND_DATA = "remind_time";
    }
}

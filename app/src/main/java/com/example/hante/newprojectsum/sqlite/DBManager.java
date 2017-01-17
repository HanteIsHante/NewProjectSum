package com.example.hante.newprojectsum.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库管理类
 */

public class DBManager {

    private static SQLiteOpenHelper mOpenHelper;
    private static DBManager instance;
    private SQLiteDatabase db;
    private AtomicInteger mOpenCounter = new AtomicInteger();



    public static synchronized void initInstance (SQLiteOpenHelper helper){
        if (instance == null){
            instance = new DBManager();
            mOpenHelper = helper;
        }
    }

    public static synchronized DBManager getInstance (Context context){
        if (instance == null) {
            throw new IllegalStateException(DBManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    public synchronized SQLiteDatabase getWriteDataBase() {
        if (mOpenCounter.incrementAndGet() == 1){
            // 新开
             db = mOpenHelper.getWritableDatabase();
        }
        return db;
    }

    public synchronized SQLiteDatabase getReadDataBase() {
        if (mOpenCounter.incrementAndGet() == 1){// 每次获取该实例mOpenCounter都通过自增来进行标记
            // 新开
            db = mOpenHelper.getReadableDatabase();
        }
        return db;
    }

    /**
     *  自减判断计数值是否为0,如果不为0,表明其它位置还在引用该实例,不能关闭数据库
     */
    public synchronized void closeDataBase() {
        if(mOpenCounter.decrementAndGet() == 0) {
            // 关闭
            db.close();
        }
    }

}

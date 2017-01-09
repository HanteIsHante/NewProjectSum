package com.example.hante.newprojectsum.ormlite.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hante.newprojectsum.ormlite.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库操作
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "sqLite-test.db";
    private static final int VERSION = 1;
    private Map<String, Dao> mDao = new HashMap<>();

    private Dao<User, Integer> userDao;

    public DatabaseHelper (Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }
//  创建数据库
    @Override
    public void onCreate (SQLiteDatabase database, ConnectionSource connectionSource) {
        // 创建数据库表
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
// 更新数据库
    @Override
    public void onUpgrade (SQLiteDatabase database, ConnectionSource connectionSource,
                           int oldVersion, int newVersion) {
        try {
            // 升级
            if (oldVersion < 2){
                 // TODO
            }
            if (oldVersion < 3){
                // TODO
            }
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单利获取该helper
     *
     * @param context context
     * @return DatabaseHelper
     */
    public static synchronized DatabaseHelper getHelper (Context context) {
        context = context.getApplicationContext();
        if(instance == null) {
            synchronized(DatabaseHelper.class) {
                if(instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao (Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if(mDao.containsKey(className)) {
            dao = mDao.get(className);
        }
        if(dao == null) {
            dao = super.getDao(clazz);
            mDao.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close () {
        super.close();
        for (String key : mDao.keySet()){
            Dao dao = mDao.get(key);
            dao = null;
        }
    }


    /**
     * 获得UserDao
     */
    public Dao<User, Integer> getUserDao () throws SQLException {
        if (userDao == null){
            userDao = getDao(User.class);
        }
        return userDao;
    }
}

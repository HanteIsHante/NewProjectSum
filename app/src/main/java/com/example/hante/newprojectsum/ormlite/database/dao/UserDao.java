package com.example.hante.newprojectsum.ormlite.database.dao;

import android.content.Context;

import com.example.hante.newprojectsum.ormlite.bean.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Dao 类操作数据表
 */

public class UserDao {

    private Context mContext;
    private Dao<User, Integer> userDao;
    private DatabaseHelper mDatabaseHelper;

    public UserDao (Context context) {
        this.mContext = context;
        try {
            mDatabaseHelper = DatabaseHelper.getHelper(context);
            userDao = mDatabaseHelper.getDao(User.class);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     * @param user 单挑数据
     * @param list 多条数据
     */
    public void add (User user, List<User> list){
        try {
            userDao.create(user);
            userDao.create(list);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据id 查询
     */
    public User getId (int id){
        try {
            return  userDao.queryForId(id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据ID 删除
     */
    public void deleteID (int id){
        try {
            userDao.deleteById(id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除集合
     */
    public void deleteUsers (){
        User userOne = new User(100, 110);
        userOne.setId(1);
        User userTwo = new User(101, 119);
        userTwo.setId(2);
        Collection<User> collections = new ArrayList<>();
        collections.add(userOne);
        collections.add(userTwo);
        try {
            userDao.delete(collections);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有的
     */
    public List<User> queryAll (){
        List<User> users = null;
        try {
             users = userDao.queryForAll();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 更改
     */
    public void updata (){
         User user = new User(109, 120);
        try {
            userDao.updateId(user, 1);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复杂 查询语句
     * 参考链接 http://www.jianshu.com/p/eafb0b4ad949
     * dao.queryBuilder.()where()方法返回一个where对象，where中提供了很多方法来进行条
     */
    public void query (){
        try {
            userDao.queryBuilder().where().eq("name", 101).query();


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


}

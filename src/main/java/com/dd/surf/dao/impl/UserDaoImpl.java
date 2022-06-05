package com.dd.surf.dao.impl;

import com.dd.surf.dao.UserDao;
import com.dd.surf.util.BaseDao;

import java.sql.ResultSet;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public int existUser(String userName, String userPass) {
        String sql = "select COUNT(*) as c from surf.user where userName = ? and userPass = ?";
        Object[] objects = {userName,userPass};
        ResultSet resultSet = executeQuery(sql,objects);
        int count = 0;
        try {
            while (resultSet.next()){
                count = resultSet.getInt("c");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int createUser(String userName, String userPass) {
        String sql = "insert into user (userName, userPass) values (?,?)";
        Object[] objects = {userName,userPass};
        return executeUpdate(sql,objects);
    }

    @Override
    public int getUserId(String userName, String userPass) {
        String sql = "select id from user where userName = ? and userPass = ?";
        Object[] objects = {userName,userPass};
        ResultSet resultSet = executeQuery(sql,objects);
        try {
            while (resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getNameByUserName(String userName){
        String sql = "select name from surf.user where userName = ?";
        Object[] objects = {userName};
        ResultSet resultSet = executeQuery(sql,objects);
        String name = userName;
        try {
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String getNameById(int id) {
        String sql = "select name from surf.user where id = ?";
        Object[] objects = {id};
        ResultSet resultSet = executeQuery(sql,objects);
        try {
            while (resultSet.next()){
                return resultSet.getString("name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
}

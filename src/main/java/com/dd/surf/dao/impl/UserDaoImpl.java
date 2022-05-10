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
}

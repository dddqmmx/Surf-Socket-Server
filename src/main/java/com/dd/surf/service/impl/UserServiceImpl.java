package com.dd.surf.service.impl;

import com.dd.surf.dao.UserDao;
import com.dd.surf.dao.impl.UserDaoImpl;
import com.dd.surf.pojo.User;
import com.dd.surf.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean login(String userName, String userPass) {
        if (existUser(userName,userPass)){
            return true;
        }else{
            return createUser(userName, userPass);
        }
    }

    @Override
    public boolean existUser(String userName, String userPass) {
        return userDao.existUser(userName, userPass) > 0;
    }

    @Override
    public boolean createUser(String userName, String userPass) {
        return userDao.createUser(userName, userPass) > 0;
    }

    @Override
    public int getUserId(String userName, String userPass) {
        return userDao.getUserId(userName,userPass);
    }

    @Override
    public String getNameByUserName(String userName) {
        return userDao.getNameByUserName(userName);
    }

    @Override
    public String getNameById(int id) {
        return userDao.getNameById(id);
    }
}

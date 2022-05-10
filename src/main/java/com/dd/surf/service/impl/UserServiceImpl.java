package com.dd.surf.service.impl;

import com.dd.surf.dao.UserDao;
import com.dd.surf.dao.impl.UserDaoImpl;
import com.dd.surf.pojo.User;
import com.dd.surf.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean login(String userName, String userPass) {
        return userDao.login(userName, userPass) > 0;
    }
}

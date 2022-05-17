package com.dd.surf.dao;

public interface UserDao {
    int existUser(String userName, String userPass);
    int createUser(String userName, String userPass);
    String getName(String userName);
}

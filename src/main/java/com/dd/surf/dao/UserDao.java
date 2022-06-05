package com.dd.surf.dao;

public interface UserDao {
    int existUser(String userName, String userPass);
    int createUser(String userName, String userPass);
    int getUserId(String userName, String userPass);
    String getNameByUserName(String userName);
    String getNameById(int id);
}

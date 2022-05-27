package com.dd.surf.service;

public interface UserService {
    boolean login(String userName,String userPass);
    boolean existUser(String userName,String userPass);
    boolean createUser(String userName,String userPass);
    String getNameByUserName(String userName);
    String getNameById(int id);
}

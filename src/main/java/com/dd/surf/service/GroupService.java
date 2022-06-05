package com.dd.surf.service;

import com.dd.surf.pojo.Group;

import java.util.List;

public interface GroupService {
    String getGroupNameById(int id);
    List<Group> getGroupList(String userName, String password);
}

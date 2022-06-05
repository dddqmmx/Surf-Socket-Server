package com.dd.surf.service.impl;

import com.dd.surf.dao.GroupDao;
import com.dd.surf.dao.impl.GroupImpl;
import com.dd.surf.pojo.Group;
import com.dd.surf.service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    GroupDao groupDao = new GroupImpl();

    @Override
    public String getGroupNameById(int id) {
        return groupDao.getGroupNameById(id);
    }

    @Override
    public List<Group> getGroupList(String userName, String password) {
        return groupDao.getGroupList(userName, password);
    }
}

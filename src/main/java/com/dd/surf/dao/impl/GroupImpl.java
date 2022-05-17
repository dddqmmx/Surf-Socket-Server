package com.dd.surf.dao.impl;

import com.dd.surf.dao.GroupDao;
import com.dd.surf.pojo.Group;
import com.dd.surf.util.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupImpl extends BaseDao implements GroupDao {

    @Override
    public List<Group> getGroupList(String userName, String password) {
        String sql = "select g.id as id,g.groupName as groupName,g.groupHead as groupHead from `group` as g left join groupmember as gm on g.id = gm.groupId where gm.userId in (select user.id from user where userName = ? and  userPass = ?)";
        Object[] objects = {userName,password};
        ResultSet resultSet = executeQuery(sql,objects);
        List<Group> groupList = new ArrayList<>();
        try {
            while (resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setGroupName(resultSet.getString("groupName"));
                group.setGroupHead(resultSet.getString("groupHead"));
                groupList.add(group);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupList;
    }
}

package com.dd.surf;

import com.dd.surf.pojo.Group;
import com.dd.surf.service.GroupService;
import com.dd.surf.service.impl.GroupServiceImpl;

import java.util.List;

public class TestSQL {
    public static void main(String[] args) {
        GroupService groupService = new GroupServiceImpl();
        List<Group> groupList=groupService.getGroupList("dddqmmx","liyan745921");
        for (Group group:groupList){
            System.out.println(group.getId()+" : "+group.getGroupName()+" : "+group.getGroupHead());
        }
    }
}

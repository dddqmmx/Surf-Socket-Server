package com.dd.surf;

import com.dd.surf.pojo.Group;
import com.dd.surf.service.GroupService;
import com.dd.surf.service.impl.GroupServiceImpl;
import org.json.JSONObject;

import java.util.List;

public class TestSQL {
    public static void main(String[] args) {
        GroupService groupService = new GroupServiceImpl();
        List<Group> groupList=groupService.getGroupList("dddqmmx","liyan745921");
        JSONObject jsonObject = new JSONObject();
        for (Group group:groupList){
            String[] groupInfo = {group.getGroupName(),group.getGroupHead()};
            jsonObject.put(String.valueOf(group.getId()),groupInfo);
        }
        System.out.println(jsonObject.toString());
    }
}

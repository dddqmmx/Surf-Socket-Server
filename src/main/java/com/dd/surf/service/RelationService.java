package com.dd.surf.service;

import com.dd.surf.pojo.Relation;

import java.util.List;

public interface RelationService {
    List<Relation> getFriendList(String userName, String userPass);
    List<Relation> gerFriendRequestList(String userName,String userPass);

}

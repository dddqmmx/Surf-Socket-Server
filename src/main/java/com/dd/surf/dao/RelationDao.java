package com.dd.surf.dao;

import com.dd.surf.pojo.Relation;

import java.util.ArrayList;
import java.util.List;

public interface RelationDao {
    List<Relation> getFriendList(String userName,String userPass);
    List<Relation> gerFriendRequestList(String userName,String userPass);
}

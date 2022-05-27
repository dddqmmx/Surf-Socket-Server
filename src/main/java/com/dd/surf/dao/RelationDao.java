package com.dd.surf.dao;

import com.dd.surf.pojo.Relation;

import java.util.ArrayList;
import java.util.List;

public interface RelationDao {
    List<Relation> getRelationList(String userName,String userPass);
}

package com.dd.surf.service;

import com.dd.surf.pojo.Relation;

import java.util.List;

public interface RelationService {
    List<Relation> getRelationList(String userName, String userPass);

}

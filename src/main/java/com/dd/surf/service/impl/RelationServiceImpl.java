package com.dd.surf.service.impl;

import com.dd.surf.dao.RelationDao;
import com.dd.surf.dao.impl.RelationDaoImpl;
import com.dd.surf.pojo.Relation;
import com.dd.surf.service.RelationService;

import java.util.List;

public class RelationServiceImpl implements RelationService {

    RelationDao relationDao = new RelationDaoImpl();

    @Override
    public List<Relation> getRelationList(String userName, String userPass) {
        return relationDao.getRelationList(userName, userPass);
    }
}

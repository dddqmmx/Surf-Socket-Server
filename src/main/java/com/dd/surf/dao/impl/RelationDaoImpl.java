package com.dd.surf.dao.impl;

import com.dd.surf.dao.RelationDao;
import com.dd.surf.pojo.Relation;
import com.dd.surf.util.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RelationDaoImpl extends BaseDao implements RelationDao {
    @Override
    public List<Relation> getRelationList(String userName,String userPass) {
        String sql = "select * from relation where relation.userId in (select user.id from user where user.userName = ? and  user.userPass = ?) and relationType in (select relationtype.id from relationtype where name = 'friend')";
        Object[] objects = {userName,userPass};
        List<Relation> relationList = new ArrayList<>();
        ResultSet resultSet = executeQuery(sql,objects);
        try {
            while (resultSet.next()){
                Relation relation = new Relation();
                relation.setId(resultSet.getInt("id"));
                relation.setUserId(resultSet.getInt("userId"));
                relation.setOtherSideId(resultSet.getInt("otherSideId"));
                relation.setRelationType(resultSet.getInt("relationType"));
                relationList.add(relation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return relationList;
    }
}

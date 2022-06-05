package com.dd.surf;

import com.dd.surf.dao.RelationDao;
import com.dd.surf.pojo.Relation;

import java.util.List;

public class TestSQL {
    public static void main(String[] args) {
        RelationDao relationDao = new RelationDaoImpl();
        List<Relation> relationList=relationDao.gerFriendRequestList("dddqmmx","liyan745921");
        //JSONObject jsonObject = new JSONObject();
        for (Relation relation:relationList){
            System.out.println(relation.getId());
        }
        //System.out.println(jsonObject.toString());
    }
}

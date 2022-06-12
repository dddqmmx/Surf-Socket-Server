package com.dd.surf.dao.impl;

import com.dd.surf.dao.MessageDao;
import com.dd.surf.pojo.Message;
import com.dd.surf.pojo.Relation;
import com.dd.surf.util.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public int getMessageCount(int contactType, int contactId) {
        String sql = "select count(1) as c from message where contactType = ? and contactId = ?";
        Object[] objects = {contactType,contactId};
        ResultSet resultSet = executeQuery(sql,objects);
        int count = 0;
        try {
            while (resultSet.next()){
                count = resultSet.getInt("c");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Message> getMessageList(int contactType, int contactId, int startRow, int showRow) {
        String sql = "select * from message where contactType = ? and contactId = ? limit ?,?";
        Object[] objects = {contactType,contactId,startRow,showRow};
        List<Message> messageList = new ArrayList<>();
        ResultSet resultSet = executeQuery(sql,objects);
        try {
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setSenderId(resultSet.getInt("senderId"));
                message.setContactType(resultSet.getInt("contactType"));
                message.setContactId(resultSet.getInt("contactId"));
                message.setMessageType(resultSet.getInt("messageType"));
                message.setMessage(resultSet.getString("message"));
                messageList.add(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messageList;
    }
}

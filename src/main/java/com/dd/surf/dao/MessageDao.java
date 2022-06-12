package com.dd.surf.dao;

import com.dd.surf.pojo.Message;

import java.util.List;

public interface MessageDao {
    int getMessageCount(int contactType,int contactId);
    List<Message> getMessageList(int contactType,int contactId,int startRow,int showRow);
}

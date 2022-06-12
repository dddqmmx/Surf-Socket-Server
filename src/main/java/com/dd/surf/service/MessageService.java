package com.dd.surf.service;

import com.dd.surf.pojo.Message;

import java.util.List;

public interface MessageService {
    int getMessageCount(int contactType,int contactId);
    List<Message> getMessageList(int contactType, int contactId, int startRow, int showRow);
}

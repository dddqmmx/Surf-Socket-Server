package com.dd.surf.service.impl;

import com.dd.surf.dao.MessageDao;
import com.dd.surf.dao.impl.MessageDaoImpl;
import com.dd.surf.pojo.Message;
import com.dd.surf.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    MessageDao messageDao = new MessageDaoImpl();

    @Override
    public int getMessageCount(int contactType, int contactId) {
        return messageDao.getMessageCount(contactType,contactId);
    }

    @Override
    public List<Message> getMessageList(int contactType, int contactId, int startRow, int showRow) {
        return messageDao.getMessageList(contactType,contactId,startRow,showRow);
    }
}

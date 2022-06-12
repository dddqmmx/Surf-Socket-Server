package com.dd.surf.util;

import com.dd.surf.pojo.Group;
import com.dd.surf.pojo.Message;
import com.dd.surf.pojo.Relation;
import com.dd.surf.service.GroupService;
import com.dd.surf.service.MessageService;
import com.dd.surf.service.RelationService;
import com.dd.surf.service.UserService;
import com.dd.surf.service.impl.GroupServiceImpl;
import com.dd.surf.service.impl.MessageServiceImpl;
import com.dd.surf.service.impl.RelationServiceImpl;
import com.dd.surf.service.impl.UserServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UDPServerThread extends Thread {


    DatagramPacket datagramPacket = null;
    String info = null;
    InetAddress inetAddress = null;
    int port = 0;
    public UDPServerThread(DatagramPacket packet,String info,InetAddress inetAddress,int port) {
        this.info = info;
        this.inetAddress = inetAddress;
        this.port = port;
    }
    @Override
    public void run() {
        super.run();

        System.out.println(info);

        JSONObject requestJson = new JSONObject(info);

        int id = 0,contactType = 0,contactId = 0,startRow = 0,showRow = 0;
        String command = null, userName = null, userPass = null;
        if (requestJson.has("command")){
            command = requestJson.getString("command");
        }
        if (requestJson.has("id")){
            id = requestJson.getInt("id");
        }
        if (requestJson.has("userName")){
            userName = requestJson.getString("userName");
        }
        if (requestJson.has("userPass")){
            userPass = requestJson.getString("userPass");
        }
        if (requestJson.has("contactType")){
            contactType = requestJson.getInt("contactType");
        }
        if (requestJson.has("contactId")){
            contactId = requestJson.getInt("contactId");
        }
        if (requestJson.has("startRow")){
            startRow = requestJson.getInt("startRow");
        }
        if (requestJson.has("showRow")){
            showRow = requestJson.getInt("showRow");
        }

        UserService userService = new UserServiceImpl();
        GroupService groupService = new GroupServiceImpl();
        RelationService relationService = new RelationServiceImpl();
        MessageService messageService = new MessageServiceImpl();

        if (command.equals("login")) {
            boolean result = userService.login(userName,userPass);
            JSONObject repostJson = new JSONObject();
            repostJson.put("result",result);
            send(repostJson);
        } else if (command.equals("getNameByUserId")) {
            String result = userService.getNameById(id);
            JSONObject repostJson = new JSONObject();
            repostJson.put("name", result);
            send(repostJson);
        } else if (command.equals("getNameByUserName")){
            String result = userService.getNameByUserName(userName);
            JSONObject repostJson = new JSONObject();
            repostJson.put("name",result);
            send(repostJson);
        } else if (command.equals("getGroupList")){
            List<Group> groupList = groupService.getGroupList(userName,userPass);
            JSONObject repostJson = new JSONObject();
            for (Group group:groupList){
                String[] groupInfo = {group.getGroupName(),group.getGroupHead()};
                repostJson.put(String.valueOf(group.getId()),groupInfo);
            }
            send(repostJson);
        } else if (command.equals("getFriendList")){
            List<Relation> relationList = relationService.getFriendList(userName,userPass);
            JSONArray jsonArray = new JSONArray();
            for (Relation relation:relationList){
                jsonArray.put(relation.getOtherSideId());
            }
            send(jsonArray.toString());
        } else if (command.equals("getFriendRequestList")){
            List<Relation> relationList = relationService.gerFriendRequestList(userName,userPass);
            JSONArray jsonArray = new JSONArray();
            for (Relation relation:relationList){
                jsonArray.put(relation.getOtherSideId());
            }
            send(jsonArray.toString());
        }else if (command.equals("getGroupNameById")){
            String result = groupService.getGroupNameById(id);
            JSONObject repostJson = new JSONObject();
            repostJson.put("name",result);
            send(repostJson);
        }else if (command.equals("getUserId")){
            int result = userService.getUserId(userName,userPass);
            JSONObject repostJson = new JSONObject();
            repostJson.put("id",result);
            send(repostJson);
        }else if (command.equals("getMessageCount")){
            int result = messageService.getMessageCount(contactType,contactId);
            JSONObject repostJson = new JSONObject();
            repostJson.put("count",result);
            send(repostJson);
        }else if (command.equals("getMessageList")){
            List<Message> messageList = messageService.getMessageList(contactType,contactId,startRow,showRow);
            JSONObject repostJson = new JSONObject();
            for (Message message : messageList){
                System.out.println("has the message in the list");
                Map<String,Object> messageInfoMap = new HashMap<>();
                messageInfoMap.put("senderId",message.getSenderId());
                messageInfoMap.put("contactType",message.getContactType());
                messageInfoMap.put("contactId",message.getContactId());
                messageInfoMap.put("messageType",message.getMessageType());
                messageInfoMap.put("message",message.getMessage());
                repostJson.put(String.valueOf(message.getId()),messageInfoMap);
            }
            System.out.println(repostJson.toString());
            send(repostJson);
        }

    }

    public boolean send(JSONObject data){
        return send(data.toString());
    }

    public boolean send(String data){
        return send(data.getBytes(StandardCharsets.UTF_8));
    }

    public boolean send(byte[] data) {
        if (data == null){
            return false;
        }
        System.out.println(new String(data));
        DatagramPacket datagramPacket =  new DatagramPacket(data, data.length, inetAddress,port);
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        socket.close();
        return true;
    }
}
package com.dd.surf.util;

import com.dd.surf.pojo.Group;
import com.dd.surf.service.GroupService;
import com.dd.surf.service.UserService;
import com.dd.surf.service.impl.GroupServiceImpl;
import com.dd.surf.service.impl.UserServiceImpl;

import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

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

        String command = null, userName = null, userPass = null;
        if (requestJson.getString("command") != null){
            command = requestJson.getString("command");
        }
        if (requestJson.getString("userName") != null){
            userName = requestJson.getString("userName");
        }
        if (requestJson.getString("userPass") != null){
            userPass = requestJson.getString("userPass");
        }

        if (command.equals("login")) {
            UserService userService = new UserServiceImpl();
            boolean result = userService.login(userName,userPass);
            JSONObject repostJson = new JSONObject();
            repostJson.put("result",result);
            send(repostJson);
        } else if (command.equals("getName")){
            UserService userService = new UserServiceImpl();
            String result = userService.getName(userName);
            JSONObject repostJson = new JSONObject();
            repostJson.put("name",result);
            send(repostJson);
        } else if (command.equals("getGroupList")){
            GroupService groupService = new GroupServiceImpl();
            List<Group> groupList = groupService.getGroupList(userName,userPass);
            JSONObject repostJson = new JSONObject();
            for (Group group:groupList){
                String[] groupInfo = {group.getGroupName(),group.getGroupHead()};
                repostJson.put(String.valueOf(group.getId()),groupInfo);
            }
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
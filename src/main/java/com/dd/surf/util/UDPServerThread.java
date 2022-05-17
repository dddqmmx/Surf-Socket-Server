package com.dd.surf.util;

import com.dd.surf.service.UserService;
import com.dd.surf.service.impl.UserServiceImpl;

import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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
        String command = requestJson.getString("command");

        String userName,userPass = null;

        if (command.equals("login")) {
            userName = requestJson.getString("userName");
            userPass = requestJson.getString("userPass");
            UserService userService = new UserServiceImpl();
            boolean result = userService.login(userName,userPass);
            JSONObject repostJson = new JSONObject();
            repostJson.put("result",result);
            send(repostJson.toString().getBytes(StandardCharsets.UTF_8));
        }

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
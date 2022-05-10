package com.dd.surf.util;

import com.dd.surf.service.UserService;
import com.dd.surf.service.impl.UserServiceImpl;
import com.mysql.cj.xdevapi.JsonArray;

import org.json.JSONObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPServerThread extends Thread {


    DatagramPacket datagramPacket = null;

    public UDPServerThread(DatagramPacket packet) {
        this.datagramPacket = packet;
    }
    @Override
    public void run() {
        super.run();

        String info = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

        System.out.println(info);

        JSONObject requestJson = new JSONObject(info);
        String command = requestJson.getString("command");

        String userName,userPass = null;

        if (command.equals("login")) {
            userName = requestJson.getString("userName");
            userPass = requestJson.getString("userPass");
            UserService userService = new UserServiceImpl();
            boolean result = userService.existUser(userName,userPass);
            JSONObject repostJson = new JSONObject();
            repostJson.put("result",result);
            send(repostJson.toString().getBytes());
        }

    }

    public boolean send(byte[] data){
        System.out.println(new String(data));
        if (data == null){
            return false;
        }
        InetAddress inetAddress = this.datagramPacket.getAddress();
        int port = this.datagramPacket.getPort();
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
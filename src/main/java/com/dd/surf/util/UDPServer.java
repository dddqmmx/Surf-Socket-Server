package com.dd.surf.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPServer extends Thread{

    public int port = 2077;

    @Override
    public void run(){
        // TODO Auto-generated method stub
        DatagramSocket socket= null;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        System.out.println(".....服务器准备启动......");

        while(true)
        {
            try {
                socket.receive(packet);
                
                //packet信息不能在Thread获取原因未知
                String info = new String(packet.getData(), 0, packet.getLength());
                InetAddress inetAddress = packet.getAddress();
                int port = packet.getPort();

                UDPServerThread udpServerThread = new UDPServerThread(packet,info,inetAddress,port);
                udpServerThread.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

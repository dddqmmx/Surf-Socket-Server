package com.dd.surf.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

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
        int count = 0;

        while(true)
        {

            try {
                socket.receive(packet);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            count++;

            UDPServerThread udpServerThread = new UDPServerThread(packet);
            udpServerThread.start();

            String info = new String(data, 0, packet.getLength());
            System.out.println("我是服务器,客户端说:" + info);
            System.out.println("该客户端IP地址为:" + packet.getAddress());
            System.out.println("客户端连接次数:" + count);

        }
    }
}

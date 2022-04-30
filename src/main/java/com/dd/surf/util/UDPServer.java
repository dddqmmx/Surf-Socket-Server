package com.dd.surf.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer extends Thread{

    public int port = 2077;

    @Override
    public void run(){
        try {
            DatagramSocket socket= new DatagramSocket(port);

            byte[] data = new byte[1024];

            DatagramPacket packet = new DatagramPacket(data, data.length);

            System.out.println("服务器启动中");

            while(true) {
                socket.receive(packet);

                UDPServerThread udpServerThread = new UDPServerThread(packet);
                udpServerThread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

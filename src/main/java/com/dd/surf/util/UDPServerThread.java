package com.dd.surf.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServerThread extends Thread {


    DatagramPacket datagramPacket = null;

    public UDPServerThread(DatagramPacket packet) {
        this.datagramPacket = packet;
    }
    @Override
    public void run() {
        super.run();

        InetAddress inetAddress = datagramPacket.getAddress();
        int port2 = datagramPacket.getPort();
        byte[] data2 =	"欢迎你的到来".getBytes();
        DatagramPacket datagramPacket2 =  new DatagramPacket(data2, data2.length, inetAddress,port2);

        String info = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        System.out.println("我是服务器,客户端说:" + info);
        System.out.println("该客户端IP地址为:" + datagramPacket.getAddress());

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            socket.send(datagramPacket2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();

    }
}
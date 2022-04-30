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
        int port = datagramPacket.getPort();

        String repost = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

        System.out.println(repost);

        byte[] responseData = null;
        DatagramPacket response =  new DatagramPacket(responseData, responseData.length, inetAddress,port);
        /*DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();*/
    }
}
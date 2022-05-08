package com.dd.surf;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class TestClient {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            InetAddress  inetAddress = InetAddress.getByName("localhost");
            int port = 2077;
            byte[] data = "用户名: 000;密码: 123".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length,inetAddress,port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);

            /*
             * 接收服务器端的数据
             */
            byte[] data2 = new byte[1024];
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
            socket.receive(packet2);
            String reply = new String(data2, 0, packet2.getLength());
            System.out.println("我是客户端,服务器说:"+reply);
            socket.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

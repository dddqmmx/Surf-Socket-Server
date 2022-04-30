package com.dd.surf;

import com.dd.surf.util.UDPServer;

public class Main {
    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
        udpServer.start();
    }
}

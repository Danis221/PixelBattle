package ru.itis.extreme_pixel_battle;

import ru.itis.extreme_pixel_battle.sockets.SocketServer;

public class Server {
    public static void main(String[] args) {
        SocketServer server = SocketServer.getInstance();
        server.start(666);
    }
}

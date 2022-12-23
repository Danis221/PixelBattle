package ru.itis.extreme_pixel_battle.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocketServer {

    private List<User> users = new CopyOnWriteArrayList<>();

    private static SocketServer socketServer;

    public boolean isEnoughPlayers() {
        return users.size() > 1;
    }

    public void start(int port) {
        new Thread() {

            ServerSocket serverSocket;
            {
                try {
                    serverSocket = new ServerSocket(port);

                    while (true) {
                        Socket userSocket = serverSocket.accept();
                        User user = new User(userSocket);
                        users.add(user);

                        user.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static SocketServer getInstance() {
        if (socketServer == null) {
            socketServer = new SocketServer();
        }
        return socketServer;
    }
    public List<User> getUsers() {
        return users;
    }
}

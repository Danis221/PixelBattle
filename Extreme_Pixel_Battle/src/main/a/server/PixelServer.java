//package ru.itis.extreme_pixel_battle.server;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PixelServer {
//
//    private static final int PORT = 5555;
//    private ServerSocket socket;
//    private final List<ClientThread> clients = new ArrayList<ClientThread>();
//
//    public void start() throws IOException {
//        socket = new ServerSocket(PORT);
//
//        while (true) {
//            Socket clientSocket = socket.accept();
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
//            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));
//
//            ClientThread clientThread = new ClientThread(input, output, this);
//            clients.add(clientThread);
//
//            new Thread(clientThread).start();
//        }
//    }
//
//
//    public void sendMessage(String message, ClientThread sender) throws IOException {
//        for (ClientThread thread : clients) {
//            if (thread.equals(sender)) {
//                continue;
//            }
//
//            thread.getOutput().write(message + "\n");
//            thread.getOutput().flush();
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        PixelServer pixelServer = new PixelServer();
//        pixelServer.start();
//    }
//}
//
////package ru.itis.extreme_pixel_battle.server;
////import ru.itis.extreme_pixel_battle.UserConfig;
////
////import java.io.BufferedReader;
////import java.io.IOException;
////import java.io.InputStreamReader;
////import java.io.PrintWriter;
////import java.net.ServerSocket;
////import java.net.Socket;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.concurrent.CopyOnWriteArrayList;
////import java.util.concurrent.CountDownLatch;
////import java.util.concurrent.ExecutorService;
////import java.util.concurrent.Executors;
////
////public class PixelServer {
////
////    private List<UserConfig> users = new CopyOnWriteArrayList<>();
////
////    private final int PLAYERS_NUMBER = 2;
////    private static PixelServer pixelServer;
////
////    public boolean isEnoughPlayers() {
////        return users.size() == PLAYERS_NUMBER;
////    }
////
////    public void start(int port) {
////        new Thread() {
////
////            ServerSocket serverSocket;
////            {
////                try {
////                    serverSocket = new ServerSocket(port);
////
////                    while (true) {
////                        Socket clientSocket = serverSocket.accept();
////                        UserConfig user = new UserConfig(clientSocket, );
////                        clients.add(client);
////
////                        client.start();
////                    }
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }.start();
////    }
////
////
////
////    public static SocketServer getInstance() {
////        if (socketServer == null) {
////            socketServer = new SocketServer();
////        }
////        return socketServer;
////    }
////
////    public List<User> getClients() {
////        return clients;
////    }
////}

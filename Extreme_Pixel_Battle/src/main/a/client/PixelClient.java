//package ru.itis.extreme_pixel_battle.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ru.itis.extreme_pixel_battle.controllers.PixelController;
//
//import java.io.*;
//import java.net.Socket;
//import java.nio.charset.StandardCharsets;
//
//public class PixelClient {
//
//    private Socket socket;
//    private ClientThread clientThread;
//
//    private PrintWriter printWriter;
//    private BufferedReader bufferedReader;
//
//    public PixelController getPixelController() { //ChatApplication()
//        return pixelController;
//    }
//
//    private final PixelController pixelController;
//
//    public PixelClient(PixelController pixelController) {
//        this.pixelController = pixelController;
//    }
//
//    public void sendMessage(String message) {
//        try {
//            String jsonMessage = new ObjectMapper().writeValueAsString(message);
//            printWriter.println(jsonMessage);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void start() throws IOException {
//        String host = pixelController.getUserConfig().getHost();
//        int port = pixelController.getUserConfig().getPort();
//
//        socket = new Socket(host, port);
//
//        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
//        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
//
//        clientThread = new ClientThread(input, output, this);
//
//        new Thread(clientThread).start();
//    }
//}

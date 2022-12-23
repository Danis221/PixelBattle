//package ru.itis.extreme_pixel_battle.server;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//
//public class ClientThread implements  Runnable {
//    private final BufferedReader input;
//    private final BufferedWriter output;
//    private PixelServer pixelServer;
//
//    public BufferedReader getInput() {
//        return input;
//    }
//
//    public BufferedWriter getOutput() {
//        return output;
//    }
//
//    public ClientThread(BufferedReader input, BufferedWriter output, PixelServer pixelServer) {
//        this.input = input;
//        this.output = output;
//        this.pixelServer = pixelServer;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                String message = input.readLine();
//                pixelServer.sendMessage(message, this);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

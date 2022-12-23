//package ru.itis.extreme_pixel_battle.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javafx.application.Platform;
//import ru.itis.extreme_pixel_battle.controllers.PixelController;
//import ru.itis.extreme_pixel_battle.model.Message;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//
//public class ClientThread implements Runnable {
//    private final BufferedReader input;
//
//
//
//    private PixelController pixelController;
//
//    public BufferedReader getInput() {
//        return input;
//    }
//
//    public BufferedWriter getOutput() {
//        return output;
//    }
//
//    private final BufferedWriter output;
//    private PixelClient pixelClient;
//
//    public ClientThread(BufferedReader input, BufferedWriter output, PixelClient pixelClient) {
//        this.input = input;
//        this.output = output;
//        this.pixelClient = pixelClient;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                String newMessage = input.readLine();
//                ObjectMapper objectMapper = new ObjectMapper();
//                Message message = objectMapper.readValue(newMessage, Message.class);
//                switch (message.getType()) {
//                    case START: {
//                        System.out.println(message);
//                        Platform.runLater(() -> pixelController.startGame());
//                        break;
//                    }
//                    case SELECT_PIXEL: {
//                        System.out.println(message);
//                        String[] value = message.getBody().split(",");
//                        Platform.runLater(() -> pixelController.fillCell(value[0], value[1]));
//                        break;
//                    }
//                    case STOP: {
//                        System.out.println(message);
//                        Platform.runLater(() -> pixelController.endGame());
//                        break;
//                    }
//                }
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//

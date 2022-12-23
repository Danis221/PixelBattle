package ru.itis.extreme_pixel_battle.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import ru.itis.extreme_pixel_battle.controllers.PixelController;
import ru.itis.extreme_pixel_battle.models.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUser extends Thread {
    private Socket user;

    private PixelController controller;

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public SocketUser(String host, int port) {
        try {
            user = new Socket(host, port);
            printWriter = new PrintWriter(user.getOutputStream(), true);

            bufferedReader = new BufferedReader(new InputStreamReader(user.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(Message message) {
        try {
            String jsonMessage = new ObjectMapper().writeValueAsString(message);
            printWriter.println(jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String futureMessage = bufferedReader.readLine();
                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(futureMessage, Message.class);

                switch (message.getType()) {
                    case START -> {
                        System.out.println(message);
                        Platform.runLater(() -> controller.startGame());
                    }
                    case SELECT_PIXEL -> {
                        System.out.println(message);
                        String[] coordinates = message.getBody().split(",");
                        Platform.runLater(() -> controller.fillCell(coordinates[0], coordinates[1]));
                    }
                    case STOP -> {
                        System.out.println(message);
                        Platform.runLater(() -> controller.endGame());
                    }
                    case SEND_MESSAGE_IN_CHAT -> {
                        String chatMessage = message.getBody();
                        Platform.runLater(() -> controller.appendMessageToConversation(chatMessage));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setController(PixelController pixelController) {
        this.controller = pixelController;
    }
}

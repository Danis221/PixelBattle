package ru.itis.extreme_pixel_battle.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.extreme_pixel_battle.models.Message;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class User extends Thread {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;
    private SocketServer socketServer;

    private boolean running = true;

    private void sendMessageToOthers(Message message, boolean isForAll) {
        for (User other: socketServer.getUsers()) {
            if (isForAll || !this.equals(other)){
                other.sendMessage(message);
            }
        }
    }

     public User(Socket socket) {
         try {
             this.socket = socket;
             socketServer = SocketServer.getInstance();

             printWriter = new PrintWriter(socket.getOutputStream(), true);
             bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
        while (running) {
            try {
                String futureMessage = bufferedReader.readLine();
                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(futureMessage, Message.class);

                switch (message.getType()) {
                    case START -> {
                        if (socketServer.isEnoughPlayers())
                            sendMessageToOthers(message, true);
                    }
                    case SELECT_PIXEL -> {
                        sendMessageToOthers(message, false);
                    }
                    case STOP -> {
                        running = false;
                        socketServer.getUsers().remove(this);
                        sendMessageToOthers(message, false);
                    }
                    case SEND_MESSAGE_IN_CHAT -> {
                        System.out.println(message);
                        sendMessageToOthers(message, false);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

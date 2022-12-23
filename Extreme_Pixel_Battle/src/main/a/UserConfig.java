//package ru.itis.extreme_pixel_battle;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ru.itis.extreme_pixel_battle.model.Message;
//
//public class UserConfig {
////    Message message = objectMapper.readValue(futureMessage, Message.class);
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    private String name;
//    private String host;
//    private int port;
//
//
//
//
//                switch (message.getType()){
//        case START:{
//            if (socketServer.isEnoughPlayers())
//                sendMessageToOthers(message, true);
//            break;
//        }
//        case SELECT_PIXEL:{
//            sendMessageToOthers(message, false);
//            break;
//        }
//        case STOP:{
//            running = false;
//            socketServer.getClients().remove(this);
//            sendMessageToOthers(message,false);
//            break;
//        }
//    }
//}

package ru.itis.extreme_pixel_battle.models;

import java.util.HashMap;
import java.util.Map;

public class Message {


    Map<String, String> headers;
    Type type;
    String body;

    public Message() {
        headers = new HashMap<>();
    }

    public Type getType() {
        return type;
    }
    public String getBody() {
        return body;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setBody(String body) {
        this.body = body;
    }




}

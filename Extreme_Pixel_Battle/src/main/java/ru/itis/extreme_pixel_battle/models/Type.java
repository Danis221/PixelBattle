package ru.itis.extreme_pixel_battle.models;

public enum Type {

    SELECT_PIXEL(""),
    STOP(""),
    START(""),
    SEND_MESSAGE_IN_CHAT("");


    private final String title;

    Type(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

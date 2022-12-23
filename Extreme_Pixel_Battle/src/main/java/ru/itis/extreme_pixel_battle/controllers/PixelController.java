package ru.itis.extreme_pixel_battle.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ru.itis.extreme_pixel_battle.models.Message;

import ru.itis.extreme_pixel_battle.models.Type;
import ru.itis.extreme_pixel_battle.sockets.SocketUser;
import ru.itis.extreme_pixel_battle.sockets.UserConfig;
import javafx.scene.control.TextArea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PixelController implements Initializable {
    private ScheduledExecutorService service;

    @FXML
    private AnchorPane pane;
    @FXML
    private Label timerLabel;
    @FXML
    private Label textLabel;
    @FXML
    private Button saveButton;
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button sendMessage;

    private TextArea input;
    private TextArea conversation;

    private AnchorPane anchorPane = null;

    private GridPane gridPane;

    private final Integer gameTime = 60;

    private final int row = 20;

    private SocketUser socketUser;
    private UserConfig userConfig;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane = createSheet();

        saveButton.setOnMouseClicked(mouseEvent -> save());
        saveButton.setVisible(false);
        pane.getChildren().add(gridPane);

        socketUser = new SocketUser("localhost", 666);
        socketUser.setController(this);
        socketUser.start();
        service = Executors.newScheduledThreadPool(2    );
        Message connectMessage = new Message();
        connectMessage.setType(Type.START);
        socketUser.sendMessage(connectMessage);

        createChat();
    }

    private void createChat(){
        anchorPane = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);


        
        AnchorPane.setTopAnchor(conversation, 100.0);
        AnchorPane.setLeftAnchor(conversation, 700.0);
        AnchorPane.setBottomAnchor(conversation, 150.0);
        AnchorPane.setRightAnchor(conversation, 100.0);

        input = new TextArea();

        AnchorPane.setBottomAnchor(input, 90.0);
        AnchorPane.setTopAnchor(input, 550.0);
        AnchorPane.setLeftAnchor(input, 700.0);
        AnchorPane.setRightAnchor(input, 100.0);


        sendMessage.setOnMouseClicked(
                mouseEvent -> {
                    String chatMessage = input.getText() + "\n";
                    conversation.appendText("you: " + chatMessage);

                    Message message = new Message();
                    message.setType(Type.SEND_MESSAGE_IN_CHAT);
                    message.setBody(chatMessage);
                    socketUser.sendMessage(message);

                    input.setText("");
                }
        );

        pane.getChildren().addAll(input, conversation);
    }

    private GridPane createSheet() {
        gridPane = new GridPane();
        gridPane.setLayoutX(14);
        gridPane.setLayoutY(100);
        gridPane.setPrefSize(500, 500);
        gridPane.setGridLinesVisible(true);
        gridPane.setDisable(true);
        textLabel.setVisible(false);

        for (int i = 0; i < row; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(30);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(30);

            gridPane.getColumnConstraints().add(colConst);
            gridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= row; j++) {
                Rectangle pixel = new Rectangle(row + 4, row + 4, Paint.valueOf("white"));
                pixel.setId(String.valueOf(i * row + j));
                pixel.setOnMouseClicked(event -> {
                    pixel.setFill(colorPicker.getValue());
                    Message message = new Message();
                    message.setType(Type.SELECT_PIXEL);
                    message.setBody(pixel.getId() + "," + colorPicker.getValue());
                    socketUser.sendMessage(message);
                });
                gridPane.add(pixel, i, j - 1, 1, 1);
            }
        }
        return gridPane;
    }


    public void startGame() {
        gridPane.setDisable(false);
        textLabel.setVisible(true);
        service.schedule(() -> Platform.runLater(this::endGame), gameTime + 1, TimeUnit.SECONDS);

        timerLabel.setText("" + gameTime);
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent ->
                timerLabel.setText("" + (Integer.parseInt(timerLabel.getText()) - 1) + "")));
        timer.setCycleCount(gameTime);
        timer.play();
    }

    public void endGame() {
        gridPane.setDisable(true);
        textLabel.setVisible(false);
        timerLabel.setText("Game over!!!");
        saveButton.setVisible(true);
        input.setEditable(false);
        input.setText("game and chat over!");
        Message message = new Message();
        message.setType(Type.STOP);
        socketUser.sendMessage(message);
    }

    private void save() {
        String path = System.getProperty("user.home") + "/Desktop/ScreenPB" + new Random().nextLong() + new Random().nextLong() + ".png";
        try {
            Robot robot = new Robot();

            BufferedImage imgReturn = robot.createScreenCapture(new java.awt.Rectangle(14, 122, 500, 500));
            try {
                ImageIO.write(imgReturn, "png", new File(path));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("pixel art сохранился " +
                        "путь: " + path);
                alert.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillCell(String id, String color) {
        Rectangle cell = (Rectangle) gridPane.getChildren().get(Integer.parseInt(id));
        cell.setFill(Paint.valueOf(color));
    }


    public void appendMessageToConversation(String message) {
        conversation.appendText("other player: " + message);
    }
}

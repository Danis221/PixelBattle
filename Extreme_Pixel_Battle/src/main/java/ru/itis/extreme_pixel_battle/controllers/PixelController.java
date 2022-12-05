package ru.itis.extreme_pixel_battle.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PixelController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label timerLabel;
    @FXML
    private Label textLabel;
    @FXML
    private Button save;
    @FXML
    private ColorPicker colorPicker;

    private GridPane gridPane;

    private final Integer gameTime = 100;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane = createSheet();
        startGame();

        save.setOnMouseClicked(mouseEvent -> save());
        pane.getChildren().add(gridPane);
    }

    private GridPane createSheet() {
        gridPane = new GridPane();
        gridPane.setLayoutX(14);
        gridPane.setLayoutY(100);
        gridPane.setPrefSize(500, 500);
        gridPane.setGridLinesVisible(true);
        gridPane.setDisable(true);
        textLabel.setVisible(false);

        for (int i = 0; i < 20; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(30);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(30);

            gridPane.getColumnConstraints().add(colConst);
            gridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 1; j <= 20; j++) {
                Rectangle pixel = new Rectangle(24, 24, Paint.valueOf("white"));
                pixel.setId(String.valueOf(i * 20 + j));
                pixel.setOnMouseClicked(event -> pixel.setFill(colorPicker.getValue()));
                gridPane.add(pixel, i, j - 1, 1, 1);
            }
        }
        return gridPane;
    }


    public void startGame() {
        gridPane.setDisable(false);
        textLabel.setVisible(true);

        timerLabel.setText("" + gameTime);
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent ->
                timerLabel.setText("" + (Integer.parseInt(timerLabel.getText()) - 1) + "")));
        timer.setCycleCount(gameTime);
        timer.play();
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
                        "пут: " + path);
                alert.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}

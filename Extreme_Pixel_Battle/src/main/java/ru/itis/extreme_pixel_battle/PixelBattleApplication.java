package ru.itis.extreme_pixel_battle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import ru.itis.extreme_pixel_battle.controllers.PixelController;


import java.io.IOException;

public class PixelBattleApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        String fxmlFile = "/ru/itis/extreme_pixel_battle/view.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(PixelBattleApplication.class.getResource(fxmlFile));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Extreme Pixel Battle!");
        stage.setMaximized(true);
        stage.setResizable(false);

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
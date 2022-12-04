package ru.itis.extreme_pixel_battle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PixelBattleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PixelBattleApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 620);
        stage.setTitle("Extreme Pixel Battle!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
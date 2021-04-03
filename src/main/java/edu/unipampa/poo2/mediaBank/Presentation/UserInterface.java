package edu.unipampa.poo2.mediaBank.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UserInterface extends Application {
    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

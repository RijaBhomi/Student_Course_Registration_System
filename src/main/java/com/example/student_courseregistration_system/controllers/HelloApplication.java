package com.example.student_courseregistration_system.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Now, we are loading the main login page to start the application.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/student_courseregistration_system/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700); // Adjust scene size as needed
        scene.getStylesheets().add(HelloApplication.class.getResource("/com/example/student_courseregistration_system/styles.css").toExternalForm());

        stage.setTitle("Student Course Registration System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.student_courseregistration_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView loginImageView; // The fx:id for the ImageView

    @FXML
    private Label loginStatus;

    // This method is called automatically when the FXML file is loaded.
    @FXML
    public void initialize() {
        try {
            // Load the image from the resources folder and set it on the ImageView.
            Image loginImage = new Image(getClass().getResource("/com/example/student_courseregistration_system/Login.png").toExternalForm());
            loginImageView.setImage(loginImage);
        } catch (Exception e) {
            System.err.println("Error loading login image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLoginButton(ActionEvent event) {
        // Here we'll simulate a successful login for now.
        // In a real application, you would validate user credentials.

        System.out.println("Login button clicked, navigating to dashboard.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_courseregistration_system/Dashboard.fxml"));
            Parent dashboardRoot = loader.load();
            Scene dashboardScene = new Scene(dashboardRoot);

            dashboardScene.getStylesheets().add(getClass().getResource("/com/example/student_courseregistration_system/styles.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("Student Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading dashboard: " + e.getMessage());
        }
    }
}

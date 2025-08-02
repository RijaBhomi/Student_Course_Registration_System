package com.example.student_courseregistration_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label studentNameHeader;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField programTextField;

    @FXML
    private TextField semesterTextField;

    @FXML
    private Button editProfileButton;

    private boolean isEditing = false;

    @FXML
    public void initialize() {
        // Load the profile picture
        try {
            Image profileImage = new Image(getClass().getResource("/com/example/student_courseregistration_system/profilePic.jpeg").toExternalForm());
            profileImageView.setImage(profileImage);

            // Apply a circular clip to the ImageView
            Circle clip = new Circle(profileImageView.getFitWidth() / 2, profileImageView.getFitHeight() / 2, profileImageView.getFitWidth() / 2);
            profileImageView.setClip(clip);
        } catch (Exception e) {
            System.err.println("Error loading profile image: " + e.getMessage());
        }

        // Populate the form with hardcoded data (to be replaced with real data from file I/O)
        nameTextField.setText("Rija Bhomi");
        emailTextField.setText("rija.bhomi@student.college.edu");
        programTextField.setText("Bachelor of Science in Computer Science");
        semesterTextField.setText("Fall 2025");

        // Display the name in the header
        studentNameHeader.setText(nameTextField.getText());
    }

    @FXML
    private void handleEditProfileClick(ActionEvent event) {
        isEditing = !isEditing;

        if (isEditing) {
            // Enable editing and change button text
            nameTextField.setEditable(true);
            emailTextField.setEditable(true);
            programTextField.setEditable(true);
            semesterTextField.setEditable(true);
            editProfileButton.setText("Save Changes");
            editProfileButton.getStyleClass().add("save-button");
        } else {
            // Save changes (in a real app, this would write to a file)
            System.out.println("Saving profile changes...");
            // TODO: Implement file I/O to save student data

            // Update the header label with the new name
            studentNameHeader.setText(nameTextField.getText());

            // Disable editing and revert button text
            nameTextField.setEditable(false);
            emailTextField.setEditable(false);
            programTextField.setEditable(false);
            semesterTextField.setEditable(false);
            editProfileButton.setText("Edit Profile");
            editProfileButton.getStyleClass().remove("save-button");
        }
    }

    @FXML
    private void handleBackClick(ActionEvent event) {
        loadScene("Dashboard.fxml", event);
    }

    // A helper method to load scenes, identical to the one in DashboardController
    private void loadScene(String fxmlFileName, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_courseregistration_system/" + fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/student_courseregistration_system/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(fxmlFileName.replace(".fxml", "").replace("Page", ""));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + fxmlFileName);
        }
    }

    // Navigation methods from the left bar (re-use for consistency)
    @FXML
    private void handleDashboardClick(ActionEvent event) {
        loadScene("Dashboard.fxml", event);
    }

    @FXML
    private void handleProfileClick(ActionEvent event) {
        // Already on the profile page, do nothing
    }

    @FXML
    private void handleRegisterCourseClick(ActionEvent event) {
        // loadScene("RegisterCourse.fxml", event);
    }

    @FXML
    private void handleViewCoursesClick(ActionEvent event) {
        loadScene("ViewCourses.fxml", event);
    }

    @FXML
    private void handleLogoutClick(ActionEvent event) {
        loadScene("LoginPage.fxml", event);
    }
}

package com.example.student_courseregistration_system.controllers;

import com.example.student_courseregistration_system.models.Student;
import com.example.student_courseregistration_system.utils.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    @FXML
    private Label welcomeMessageLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private PieChart courseProgressChart;

    @FXML
    private ImageView profileImageView;

    // This is the main BorderPane that holds the whole layout.
    @FXML
    private BorderPane mainPane;

    @FXML
    public void initialize() {
        // This debug statement helps you check if the FXML field has been injected
        if (mainPane == null) {
            System.err.println("CRITICAL ERROR: mainPane is null! Check if the root BorderPane in your FXML has fx:id=\"mainPane\".");
        }

        // Load student data from the DataManager and update the labels
        Student student = DataManager.loadStudentData();
        welcomeMessageLabel.setText("Welcome back, " + student.getName() + "!");
        studentNameLabel.setText(student.getName());

        // Load and clip the profile picture
        try {
            URL imageUrl = getClass().getResource("/com/example/student_courseregistration_system/profilePic.jpeg");
            if (imageUrl != null) {
                Image profileImage = new Image(imageUrl.toExternalForm());
                profileImageView.setImage(profileImage);
                Circle clip = new Circle(profileImageView.getFitWidth() / 2, profileImageView.getFitHeight() / 2, profileImageView.getFitWidth() / 2);
                profileImageView.setClip(clip);
            } else {
                System.err.println("Profile image not found at the specified path.");
            }
        } catch (Exception e) {
            System.err.println("Error loading profile image: " + e.getMessage());
            e.printStackTrace();
        }

        // Pie chart data setup
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Registered Courses", 3),
                        new PieChart.Data("Available Courses", 2),
                        new PieChart.Data("Courses Completed", 1),
                        new PieChart.Data("Courses In Progress", 2)
                );
        courseProgressChart.setData(pieChartData);
        courseProgressChart.setLabelsVisible(true);
        courseProgressChart.setStartAngle(90);
        courseProgressChart.setTitle("Your Course Status");
    }

    @FXML
    private void handleDashboardClick(ActionEvent event) {
        // We will do nothing here, as we are already on the dashboard.
        System.out.println("Dashboard button clicked, reloading dashboard content...");
    }

    @FXML
    private void handleProfileClick(ActionEvent event) {
        System.out.println("Profile button clicked, loading profile page...");
        loadPage("ProfilePage.fxml");
    }

    @FXML
    private void handleRegisterCourseClick(ActionEvent event) {
        System.out.println("Register Course button clicked! Loading registration page...");
        // CORRECTED: Changed "RegisterCourses.fxml" to "RegisterCourse.fxml"
        loadPage("RegisterCourse.fxml");
    }

    @FXML
    private void handleViewCoursesClick(ActionEvent event) {
        System.out.println("My Courses button clicked!");
        loadPage("ViewCourses.fxml");
    }

    @FXML
    private void handleViewAllCourses(ActionEvent event) {
        System.out.println("View All Courses button clicked!");
        loadPage("ViewCourses.fxml");
    }

    @FXML
    private void handleLogoutClick(ActionEvent event) {
        System.out.println("Logout button clicked!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_courseregistration_system/LoginPage.fxml"));
            Parent loginRoot = loader.load();
            Scene loginScene = new Scene(loginRoot);

            loginScene.getStylesheets().add(getClass().getResource("/com/example/student_courseregistration_system/styles.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Student Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading login page: " + e.getMessage());
        }
    }

    /**
     * Helper method to load new FXML content into the center of the main BorderPane.
     * This method is now more robust and correctly handles the content loading.
     *
     * @param fxmlFileName The name of the FXML file to load.
     */
    private void loadPage(String fxmlFileName) {
        try {
            Parent newContent = FXMLLoader.load(getClass().getResource("/com/example/student_courseregistration_system/" + fxmlFileName));
            if (newContent != null) {
                // IMPORTANT: We only set the center content.
                // The left, top, and right sections from the main Dashboard.fxml remain.
                if (mainPane != null) {
                    mainPane.setCenter(newContent);
                } else {
                    System.err.println("Failed to load page: mainPane is null. Cannot set center content.");
                }
            } else {
                System.err.println("Failed to load FXML: " + fxmlFileName + ". getResource returned null.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + fxmlFileName);
        }
    }
}

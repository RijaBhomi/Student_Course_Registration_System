package com.example.student_courseregistration_system.controllers;

import com.example.student_courseregistration_system.models.Course;
import com.example.student_courseregistration_system.utils.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterCourseController {

    @FXML
    private ListView<Course> availableCoursesList;
    @FXML
    private ListView<Course> registeredCoursesList;
    @FXML
    private Button registerButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button backButton;

    private ObservableList<Course> allAvailableCourses;
    private ObservableList<Course> currentlyRegisteredCourses;

    @FXML
    public void initialize() {
        // Initialize the lists
        allAvailableCourses = FXCollections.observableArrayList();
        currentlyRegisteredCourses = FXCollections.observableArrayList();

        // Load all courses and separate them into available and registered
        List<Course> loadedCourses = DataManager.loadAllCourses();
        List<Course> registeredCoursesFromFile = DataManager.loadRegisteredCourses();

        List<String> registeredCourseCodes = registeredCoursesFromFile.stream()
                .map(Course::getCourseCode)
                .collect(Collectors.toList());

        for (Course course : loadedCourses) {
            if (registeredCourseCodes.contains(course.getCourseCode())) {
                course.setRegistered(true); // Mark the course as registered
                currentlyRegisteredCourses.add(course);
            } else {
                course.setRegistered(false); // Ensure it's marked as not registered
                allAvailableCourses.add(course);
            }
        }

        // Set the items for the ListViews
        availableCoursesList.setItems(allAvailableCourses);
        registeredCoursesList.setItems(currentlyRegisteredCourses);
    }

    @FXML
    private void handleRegisterCourse(ActionEvent event) {
        Course selectedCourse = availableCoursesList.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourse.setRegistered(true);
            allAvailableCourses.remove(selectedCourse);
            currentlyRegisteredCourses.add(selectedCourse);
            DataManager.saveRegisteredCourses(new ArrayList<>(currentlyRegisteredCourses));
        }
    }

    @FXML
    private void handleRemoveCourse(ActionEvent event) {
        Course selectedCourse = registeredCoursesList.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCourse.setRegistered(false);
            currentlyRegisteredCourses.remove(selectedCourse);
            allAvailableCourses.add(selectedCourse);
            DataManager.saveRegisteredCourses(new ArrayList<>(currentlyRegisteredCourses));
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_courseregistration_system/Dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}

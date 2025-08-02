module com.example.student_courseregistration_system {
    requires javafx.controls;
    requires javafx.fxml;

    // The root package is now empty and does not need to be opened or exported.
    // opens com.example.student_courseregistration_system to javafx.fxml;
    // exports com.example.student_courseregistration_system;

    // We now have a separate controllers package, so we need to export it.
    opens com.example.student_courseregistration_system.controllers to javafx.fxml;
    exports com.example.student_courseregistration_system.controllers;

    // Similarly, we need to open the models package to FXML as well.
    opens com.example.student_courseregistration_system.models to javafx.fxml;
    exports com.example.student_courseregistration_system.models;
}

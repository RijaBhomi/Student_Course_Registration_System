package com.example.student_courseregistration_system.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {

    private final SimpleStringProperty courseCode;
    private final SimpleStringProperty courseName;
    private final SimpleIntegerProperty credits;
    private final SimpleStringProperty professor;
    private final SimpleBooleanProperty isRegistered;

    public Course(String courseCode, String courseName, int credits, String professor, boolean isRegistered) {
        this.courseCode = new SimpleStringProperty(courseCode);
        this.courseName = new SimpleStringProperty(courseName);
        this.credits = new SimpleIntegerProperty(credits);
        this.professor = new SimpleStringProperty(professor);
        this.isRegistered = new SimpleBooleanProperty(isRegistered);
    }

    public String getCourseCode() {
        return courseCode.get();
    }

    public SimpleStringProperty courseCodeProperty() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode.set(courseCode);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public String getName() {
        return getCourseName();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public int getCredits() {
        return credits.get();
    }

    public SimpleIntegerProperty creditsProperty() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits.set(credits);
    }

    public String getProfessor() {
        return professor.get();
    }

    public SimpleStringProperty professorProperty() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor.set(professor);
    }

    public boolean getIsRegistered() {
        return isRegistered.get();
    }

    public SimpleBooleanProperty registeredProperty() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered.set(registered);
    }

    @Override
    public String toString() {
        return getCourseCode() + " - " + getCourseName();
    }
}

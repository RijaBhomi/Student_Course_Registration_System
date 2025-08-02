package com.example.student_courseregistration_system.models;

import java.util.ArrayList;
import java.util.List;

// This class represents the student's profile and registered courses.
public class Student {
    private String name;
    private String email;
    private String program;
    private String semester;
    private List<Course> registeredCourses;

    // Constructor to create a new Student object with default values.
    public Student(String name, String email, String program, String semester) {
        this.name = name;
        this.email = email;
        this.program = program;
        this.semester = semester;
        this.registeredCourses = new ArrayList<>();
    }

    // Getter and setter methods for the student's details.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public void addRegisteredCourse(Course course) {
        this.registeredCourses.add(course);
    }
}

package com.example.student_courseregistration_system.utils;

import com.example.student_courseregistration_system.models.Course;
import com.example.student_courseregistration_system.models.Student;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String STUDENT_DATA_FILE = "student_data.txt";
    private static final String REGISTERED_COURSES_FILE = "registered_courses.txt";

    // Helper method to get the user-specific application directory
    private static String getAppDirectory() {
        String userHome = System.getProperty("user.home");
        File appDir = new File(userHome, "StudentCourseApp");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir.getAbsolutePath();
    }

    // Method to load all student data from the file.
    public static Student loadStudentData() {
        File file = new File(getAppDirectory(), STUDENT_DATA_FILE);
        String name = "Rija Bhomi";
        String email = "rija.bhomi@student.college.edu";
        String program = "Bachelor of Science in Computer Science";
        String semester = "Fall 2025";

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                name = reader.readLine();
                email = reader.readLine();
                program = reader.readLine();
                semester = reader.readLine();
            } catch (IOException e) {
                System.err.println("Error reading student data file: " + e.getMessage());
            }
        }

        Student student = new Student(name, email, program, semester);
        student.setRegisteredCourses(loadRegisteredCourses());
        return student;
    }

    // Method to save all student data to the file.
    public static void saveStudentData(Student student) {
        File file = new File(getAppDirectory(), STUDENT_DATA_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(student.getName());
            writer.newLine();
            writer.write(student.getEmail());
            writer.newLine();
            writer.write(student.getProgram());
            writer.newLine();
            writer.write(student.getSemester());
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    // Loads a hardcoded list of all possible courses
    public static List<Course> loadAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Programming", 3, "Dr. Smith", false));
        courses.add(new Course("ML202", "Machine Learning", 4, "Bidhan Sir", true));
        courses.add(new Course("PHYS101", "General Physics", 4, "Dr. Williams", false));
        courses.add(new Course("ENGL101", "Composition and Rhetoric", 3, "Prof. Davis", false));
        courses.add(new Course("HIST205", "World History II", 3, "Dr. Brown", false));
        courses.add(new Course("DSIT306", "Data Mining", 3, "Dipshan Sir", true));
        courses.add(new Course("APIT705", "Advanced Programming", 3, "Subit Sir", true));
        courses.add(new Course("TOCIT205", "Theory of Computation", 3, "Bishal Sir", false));
        return courses;
    }

    // Saves a list of registered courses to a file.
    public static void saveRegisteredCourses(List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getAppDirectory() + File.separator + REGISTERED_COURSES_FILE))) {
            for (Course course : courses) {
                writer.println(course.getCourseCode() + "," + course.getCourseName() + "," + course.getCredits() + "," + course.getProfessor());
            }
        } catch (IOException e) {
            System.err.println("Error saving registered courses: " + e.getMessage());
        }
    }

    // Loads the list of registered courses from a file.
    public static List<Course> loadRegisteredCourses() {
        List<Course> courses = new ArrayList<>();
        if (!Files.exists(Paths.get(getAppDirectory() + File.separator + REGISTERED_COURSES_FILE))) {
            return courses; // Return empty list if file doesn't exist
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getAppDirectory() + File.separator + REGISTERED_COURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Updated to check for 4 parts
                    try {
                        String courseCode = parts[0];
                        String courseName = parts[1];
                        int credits = Integer.parseInt(parts[2]);
                        String professor = parts[3];
                        courses.add(new Course(courseCode, courseName, credits, professor, true));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in registered courses file: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading registered courses: " + e.getMessage());
        }
        return courses;
    }

    // Deletes a registered course by its course code and saves the updated list.
    public static void deleteRegisteredCourse(String courseCode) {
        List<Course> courses = loadRegisteredCourses();
        courses.removeIf(course -> course.getCourseCode().equals(courseCode));
        saveRegisteredCourses(courses); // Save the updated list
    }
}

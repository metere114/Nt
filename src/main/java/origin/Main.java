package origin;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDB();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> printDatabase();
                case 2 -> addClass(scanner);
                case 3 -> registerForClass(scanner);
                case 4 -> addGrade(scanner);
                case 5 -> deleteCourse(scanner);
                case 6 -> displayMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Print Lessons table");
        System.out.println("2. Add new class to the database");
        System.out.println("3. Register for the class");
        System.out.println("4. Add grade to person through ID");
        System.out.println("5. Delete course from database");
        System.out.println("6. Print the list of command on console");
        System.out.println("0. Exit");
    }

    private static void printDatabase() {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM LESSONS")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String lesson = resultSet.getString("LESSON");
                String start = resultSet.getString("START"); // Correct handling of time type data
                String finish = resultSet.getString("FINISH"); // Correct handling of time type data
                String student = resultSet.getString("STUDENT");
                int grade = resultSet.getInt("GRADE");

                Lessons lessons = new Lessons(id, lesson, start, finish, student, grade);
                System.out.println(lessons);
            }
        } catch (SQLException e) {
            System.out.println("Error accessing database: " + e.getMessage());
        }
    }



    private static void addClass(Scanner scanner) {
        System.out.println("Enter class details:");
        System.out.print("Lesson Name: ");
        String lessonName = scanner.nextLine();
        System.out.print("Lesson Start Time (HH:mm): ");
        String startTime = scanner.nextLine();
        System.out.print("Lesson End Time (HH:mm): ");
        String endTime = scanner.nextLine();

        String insertQuery = "INSERT INTO LESSONS (LESSON, START, FINISH) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, lessonName);
            preparedStatement.setString(2, "time(" + startTime + ":00)");
            preparedStatement.setString(3, "time(" + endTime + ":00)");

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Class added successfully.");
            } else {
                System.out.println("Failed to add class.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }


    private static void registerForClass(Scanner scanner) {
        System.out.println("Registering for a class");
        System.out.print("Please enter the student's name: ");
        String studentName = scanner.nextLine();

        System.out.print("Please enter the ID of the class to register for: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric class ID.");
            scanner.next(); // Clear the invalid input
            System.out.print("Please enter the ID of the class to register for: ");
        }
        int classId = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after reading an integer

        String updateQuery = "UPDATE LESSONS SET STUDENT = ? WHERE ID = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, studentName);
            preparedStatement.setInt(2, classId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration successful.");
            } else {
                System.out.println("Registration failed. Please check the class ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    private static void addGrade(Scanner scanner) {
        System.out.println("Adding grade to a student");
        System.out.print("Please enter the ID of the class to which you want to add a grade: ");
        int classId = scanner.nextInt();
        System.out.print("Please enter the grade: ");
        int grade = scanner.nextInt();

        // Calling scanner.nextLine() clears the input buffer
        scanner.nextLine();

        String updateQuery = "UPDATE LESSONS SET GRADE = ? WHERE ID = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setInt(1, grade);
            preparedStatement.setInt(2, classId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Grade added successfully.");
            } else {
                System.out.println("Failed to add grade. Please check the class ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    private static void deleteCourse(Scanner scanner) {
        System.out.println("Deleting a course");
        System.out.print("Please enter the ID of the course you want to delete: ");
        int courseId = scanner.nextInt();

        // Calling scanner.nextLine() clears the input buffer
        scanner.nextLine();

        String deleteQuery = "DELETE FROM LESSONS WHERE ID = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, courseId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Failed to delete course. Please check the course ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

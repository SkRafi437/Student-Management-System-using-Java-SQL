import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseUtil dbUtil = new DatabaseUtil();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:  // Add student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    Student newStudent = new Student(0, name, age, grade);
                    try {
                        dbUtil.addStudent(newStudent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:  // View students
                    try {
                        dbUtil.viewStudents();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:  // Update student
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine();
                    try {
                        dbUtil.updateStudent(updateId, newName, newAge, newGrade);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:  // Delete student
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    try {
                        dbUtil.deleteStudent(deleteId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:  // Exit
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    } }

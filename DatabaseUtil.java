import java.sql.*;
public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = ""; // Update with your MySQL password

    // Get a connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add a new student to the database
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Students (StudentName, Age, Grade) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getGrade());
            stmt.executeUpdate();
            System.out.println("Student added successfully!");
        }
    }

    // View all students
    public void viewStudents() throws SQLException {
        String query = "SELECT * FROM Students";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("StudentID");
                String name = rs.getString("StudentName");
                int age = rs.getInt("Age");
                String grade = rs.getString("Grade");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade);
            }
        }
    }


    // Update a student's record
    public void updateStudent(int id, String newName, int newAge, String newGrade) throws SQLException {
        String query = "UPDATE Students SET StudentName=?, Age=?, Grade=? WHERE StudentID=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, newAge);
            stmt.setString(3, newGrade);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Student updated successfully!");
        }
    }


    // Delete a student
    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM Students WHERE StudentID=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Student deleted successfully!");
        }
    }
}

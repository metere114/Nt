package origin;

import java.sql.*;
public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:db.db";

    public static void initializeDB() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS LESSONS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "LESSON VARCHAR(255), " +
                "START TEXT, " +
                "FINISH TEXT, " +
                "STUDENT VARCHAR(255), " +
                "GRADE INT);";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'LESSONS' has been created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}

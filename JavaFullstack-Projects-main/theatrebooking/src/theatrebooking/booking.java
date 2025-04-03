package theatrebooking;
import java.sql.*;
public class booking {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/seatbooking";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "120405brthy";

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}


package inventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:resources/database.db";  // Database file will be created in the project folder
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        String createUsersTable = Queries.CREATE_USERS_TABLE;

        String createProductsTable = Queries.CREATE_PRODUCTS_TABLE;

        String createSalesTable = Queries.CREATE_SALES_TABLE;

        String insertSampleUsers = "INSERT INTO Users (username, password, role) "
                + "SELECT 'admin', 'admin123', 'admin' WHERE NOT EXISTS (SELECT 1 FROM Users WHERE username = 'admin');";

        String insertSampleUsers2 = "INSERT INTO Users (username, password, role) "
                + "SELECT 'user', 'user123', 'user' WHERE NOT EXISTS (SELECT 1 FROM Users WHERE username = 'user');";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createProductsTable);
            stmt.execute(createSalesTable);
            stmt.execute(insertSampleUsers);
            stmt.execute(insertSampleUsers2);

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    public static String checkUserType(String username){
        String query = Queries.GET_USERS_ROLE;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Set parameters to prevent SQL injection
            stmt.setString(1, username);
            
            // Execute the query and check if a result is returned
            ResultSet rs = stmt.executeQuery();

            // Sends either amdin or user, if there is no error
            if (rs.next()) {
                return rs.getString("role");
            } else {
                System.out.println("Something Went Wrong");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error checking credentials: " + e.getMessage());
            return "";
        }
    } 

}

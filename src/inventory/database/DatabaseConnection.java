package inventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:resources/inventory.db";  // Path to SQLite DB file

    // Method to connect to SQLite
    public static Connection connect() {
        try {
            // Create and return the SQLite connection
            return DriverManager.getConnection(DATABASE_URL);
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    // Method to initialize the database by running the SQL script
    public static void initializeDatabase() {
        // Way to load and execute the SQL file
        try (Connection conn = connect();
             BufferedReader reader = new BufferedReader(new InputStreamReader(DatabaseConnection.class.getClassLoader().getResourceAsStream("database.sql")))) {
            
            String line;
            StringBuilder sqlScript = new StringBuilder();

            // Read each line of the database.sql file and build the SQL string
            while ((line = reader.readLine()) != null) {
                sqlScript.append(line).append("\n");
            }

            // Execute the SQL commands
            Statement stmt = conn.createStatement();
            stmt.execute(sqlScript.toString());

            System.out.println("Database initialized.");

        } catch (IOException e) {
            System.out.println("Error reading the SQL script: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error executing SQL: " + e.getMessage());
        }
    }

    public static boolean checkUserCredentials(String username, String password) {
        String query = Queries.CHECK_USER_CREDENTIALS;  // Use the query from Queries.java
        
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Set parameters to prevent SQL injection
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            // Execute the query and check if a result is returned
            ResultSet rs = stmt.executeQuery();

            // If a matching user is found, the result set will contain the record
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error checking credentials: " + e.getMessage());
            return false;
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

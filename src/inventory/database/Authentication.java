package inventory.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authentication {
    public static boolean checkUserCredentials(String username, String password) {
        String query = Queries.CHECK_USER_CREDENTIALS;  // Use query from Queries.java
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Set parameters to prevent SQL injection
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            // Execute the query and check if a result is returned
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If a matching user is found, return true

        } catch (Exception e) {
            System.out.println("Error checking credentials: " + e.getMessage());
            return false;
        }
    }
}

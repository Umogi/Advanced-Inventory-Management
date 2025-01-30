package inventory.database;

public class Queries {

    public static final String CHECK_USER_CREDENTIALS = 
        "SELECT * FROM Users WHERE username = ? AND password = ?";

    
    public static final String CREATE_USER = 
        "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
    
    public static final String GET_USERS_ROLE = 
        "SELECT role FROM Users WHERE username = ?";
}
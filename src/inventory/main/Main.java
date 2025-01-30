package inventory.main;

import java.util.Scanner;
import java.io.Console;

import inventory.database.DatabaseConnection;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Setting up resources
        Scanner scan = new Scanner(System.in);
        Console console = System.console();

        // Call the initialization method to run the SQL script
        DatabaseConnection.initializeDatabase();

        // Application logic goes here
        System.out.println("Inventory management system initialized.");

        while (true) {
            System.err.println("==== Log In Page ====");
            
            System.out.print("Username: ");
            String username = scan.nextLine();
            
            char[] passwordArray = console.readPassword("Password: ");
            String password = new String(passwordArray);

            // Chechs User Credentials
            
            if (DatabaseConnection.checkUserCredentials(username, password) == true) {
                System.out.println("Connection Succesful!");

                if (DatabaseConnection.checkUserType(username).equals("admin")) {
                    
                }else if (DatabaseConnection.checkUserType(username).equals("user")) {
                    
                }else{
                    System.out.println("There was an error acured, please try again");
                }
            }else{
                System.out.println("Username Or Password is wrong!");
            }
        }
    }
}
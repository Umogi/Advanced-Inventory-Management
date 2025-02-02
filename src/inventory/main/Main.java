package inventory.main;

import java.util.Scanner;

import inventory.database.*;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Setting up resources
        Scanner scan = new Scanner(System.in);
        DatabaseConnection.initializeDatabase();

        while (true) {
            System.err.println("==== Log In Page ====");
            
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();

            // Chechs User Credentials
            if (Authentication.checkUserCredentials(username, password) == true) {
                System.out.println("Connection Succesful!\n");

                if (DatabaseConnection.checkUserType(username).equals("admin")) {
                    AdminInteface.main(args);
                }else if (DatabaseConnection.checkUserType(username).equals("user")) {
                    UserInterface.main(args);
                }else{
                    System.out.println("There was an error acured, please try again");
                }
            }else{
                System.out.println("Username Or Password is wrong!");
            }
        }
    }
}

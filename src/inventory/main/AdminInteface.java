package inventory.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import inventory.database.DatabaseConnection;
import inventory.database.Queries;

public class AdminInteface {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n\n\n===== User Inteface =====");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Reports");
            System.out.println("5. Create User");
            System.out.println("6. Delete User");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            int choice = scan.nextInt();

            if (choice == 1) {
                addProduct();
            }else if (choice == 2) {
                updateProduct();
            }else if (choice == 3) {
                deleteProduct();
            }else if (choice == 4) {
                ReportGenerator.generateReports();
            }else if (choice == 5) {
                createUser();
            }else if (choice == 6) {
                deleteUser();
            }else if (choice == 7) {
                break;
            }else{
                System.out.println("This comand does not exist!");
            }
        }    
    }

    @SuppressWarnings("resource")
    public static void addProduct(){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("===== Add Item =====");
        System.out.print("Name of Product: ");
        String productName = scan.nextLine();

        System.out.print("Quantity of Product: ");
        int productQuantity = scan.nextInt();

        System.out.print("Price of Product: ");
        int productPrice = scan.nextInt();

        System.out.print("Supplier of Product: ");
        String productSupplier = scan.nextLine();

        System.out.print("Expiry Date of Product (yyyy-mm-dd): ");
        String productExpiryDate = scan.nextLine();

        String insertProduct = Queries.INSERT_PRODUCT;

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement insertStmt = conn.prepareStatement(insertProduct)) {

                insertStmt.setString(1, productName);
                insertStmt.setInt(2, productQuantity);
                insertStmt.setInt(3, productPrice);
                insertStmt.setString(4, productSupplier);
                insertStmt.setString(5, productExpiryDate);
            
                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sale recorded successfully!");
                } else {
                    System.out.println("Error: Sale not recorded.");
                }

        }catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }

    }

    @SuppressWarnings("resource")
    public static void updateProduct(){
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Update Product =====");
            System.out.println("1. Name");
            System.out.println("2. Quantity");
            System.out.println("3. Price");
            System.out.println("4. Supplier");
            System.out.println("5. ExpireDate");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int choice = scan.nextInt();

            if (choice == 1) {
                System.out.print("ID of product: ");
                int id = scan.nextInt();

                scan.nextLine();

                System.out.print("New Name of product: ");
                String newName = scan.nextLine();

                ProductUpdate.updateProductName(id, newName);

            }else if (choice == 2) {
                System.out.print("ID of product: ");
                int id = scan.nextInt();

                scan.nextLine();

                System.out.print("New Quantity of product: ");
                int newQuantity = scan.nextInt();

                ProductUpdate.updateProductQuantity(id, newQuantity);

            }else if (choice == 3) {
                System.out.print("ID of product: ");
                int id = scan.nextInt();

                scan.nextLine();

                System.out.print("New Price of product: ");
                int newPrice = scan.nextInt();

                ProductUpdate.updateProductPrice(id, newPrice);

            }else if (choice == 4) {
                System.out.print("ID of product: ");
                int id = scan.nextInt();

                scan.nextLine();

                System.out.print("New Supplier of product: ");
                String newSupplier = scan.nextLine();

                ProductUpdate.updateProductSupplier( id, newSupplier);

            }else if (choice == 5) {
                System.out.print("ID of product: ");
                int id = scan.nextInt();

                scan.nextLine();

                System.out.print("New Expiry Date of product: ");
                String newExpiryDate = scan.nextLine();

                ProductUpdate.updateProductExpiryDate( id, newExpiryDate);

            }else if (choice == 6) {
                break;
            }else{
                System.out.println("This comand does not exist!");
            }

        }
        
    }

    @SuppressWarnings("resource")
    public static void deleteProduct(){
        Scanner scan = new Scanner(System.in);

        System.out.println("\n===== Delete Product =====");
        System.out.print("Selecte Product ID: ");
        int Id = scan.nextInt();

        String deleteProduct = Queries.DELETE_PRODUCT;

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement stmt = conn.prepareStatement(deleteProduct)) {
                
            stmt.setInt(1, Id);
            stmt.execute(deleteProduct);

        }catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    public static void createUser(){
        Scanner scan = new Scanner(System.in);

        System.out.println("\n===== Create User =====");

        System.out.println("Username: ");
        String username = scan.nextLine();
        System.out.println("Passwor: ");
        String password = scan.nextLine();

        String insertUsers = "INSERT INTO Users (username, password, role) "
                + "SELECT '"+username+"', '"+password+"', 'user';";
        
        try (Connection conn = DatabaseConnection.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(insertUsers);
        }catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    public static void deleteUser(){
        Scanner scan = new Scanner(System.in);

        System.out.println("\n===== Delete User =====");
        System.out.print("Selecte User ID: ");
        int Id = scan.nextInt();

        String deleteUser = Queries.DELETE_USER;

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement stmt = conn.prepareStatement(deleteUser)) {

            stmt.setInt(1, Id);
            stmt.execute(deleteUser);

        }catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

}

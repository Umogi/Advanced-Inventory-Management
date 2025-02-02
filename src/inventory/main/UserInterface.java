package inventory.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import inventory.database.DatabaseConnection;
import inventory.database.Queries;


public class UserInterface {
    @SuppressWarnings("resource")
    public static void main(String[] args) {    
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n\n\n===== User Inteface =====");
            System.out.println("1. Serach Product");
            System.out.println("2. Make Sales");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = scan.nextInt();

            if (choice == 1) {
                searchProducts();
            }else if (choice == 2) {
                Sales.newSale();
            }else if (choice == 3) {
                break;
            }else{
                System.out.println("This comand does not exist!");
            }
        }
    }
    
    @SuppressWarnings("resource")
    public static void searchProducts(){
        Scanner scan = new Scanner(System.in);

        System.out.println("\n===== Seraching Product =====");
        System.out.print("Search for poduct:");
        String productSearch = scan.nextLine();
            
        int amount = searchProductByName(productSearch);

        if (amount > 0) {
            System.out.println("Select the product you want to change");
            System.out.print("Product ID: ");
            int id = scan.nextInt();

            System.out.print("What is the new Quantiry: ");
            int newQuantity = scan.nextInt();

            ProductUpdate.updateProductQuantity(id, newQuantity);
        }
    }

    public static int searchProductByName(String productName) {
        String query = Queries.SEARCH_PRODUCT_BY_NAME;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + productName + "%");  // Allows partial matching
            ResultSet rs = stmt.executeQuery();

            int amount = 0;

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Quantity: " + rs.getInt("quantity") +
                        ", Price: $" + rs.getDouble("price"));
                amount++;
            }

            if (!found) {
                System.out.println("No products found.");
            }

            return amount;

        } catch (Exception e) {
            System.out.println("Error searching product: " + e.getMessage());
            return 0;
        }
    }

}
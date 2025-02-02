package inventory.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import inventory.database.*;

public interface Sales {

    @SuppressWarnings("resource")
    public static void newSale() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n\n===== Sale =====");
            
            System.out.print("Product ID Sold: ");
            int productSoldID = scan.nextInt();

            System.out.print("Quantity sold: ");
            int quantitySold = scan.nextInt();
            
            scan.nextLine();

            System.out.print("Sale Date (yyyy-mm-dd): ");
            String dateSold = scan.nextLine();

            String query = Queries.SEARCH_PRICE_BY_ID;
            
            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, productSoldID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    double productPrice = rs.getDouble("price");
                    double totalPrice = productPrice * quantitySold;

                    String insertSaleQuery = Queries.INSERT_SALE;

                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSaleQuery)) {
                        insertStmt.setInt(1, productSoldID);
                        insertStmt.setInt(2, quantitySold);
                        insertStmt.setString(3, dateSold);
                        insertStmt.setDouble(4, totalPrice);

                        int rowsAffected = insertStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Sale recorded successfully!");
                        } else {
                            System.out.println("Error: Sale not recorded.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error inserting sale into database: " + e.getMessage());
                        break;
                    }
                } else {
                    System.out.println("Error: Product not found.");
                    break;
                }

            } catch (Exception e) {
                System.out.println("Error searching for product: " + e.getMessage());
                break;
            }
        }
    }
}

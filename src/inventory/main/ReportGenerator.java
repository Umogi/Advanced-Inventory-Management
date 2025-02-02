package inventory.main;

import java.sql.*;

import inventory.database.*;

public class ReportGenerator {

    public static void generateReports() {
        totalProductsSold();
        totalRevenue();
        salesAndStockReport();
        expirationWarning();
    }

    public static void totalProductsSold() {
        String query = Queries.TOTAL_PRODUCTS_SOLD_REPORT;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                int totalSold = rs.getInt("total_sold");
                System.out.println("Product: " + productName + ", Total Sold: " + totalSold);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching total products sold: " + e.getMessage());
            return;
        }
    }

    public static void totalRevenue() {
        String query = Queries.TOTAL_REVENUE_REPORT;
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                double totalRevenue = rs.getDouble("total_revenue");
                System.out.println("Total Revenue: " + totalRevenue);
            }
    
        } catch (SQLException e) {
            System.out.println("Error fetching total revenue: " + e.getMessage());
            return;
        }
    }

    public static void salesAndStockReport() {
        String query = Queries.SALE_AND_STOCK_REPORT;
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
                 
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                String productName = rs.getString("name");
                int totalSold = rs.getInt("total_sold");
                int remainingStock = rs.getInt("remaining_stock");
                System.out.println("Product: " + productName + ", Total Sold: " + totalSold + ", Remaining Stock: " + remainingStock);
            }
    
        } catch (SQLException e) {
            System.out.println("Error fetching sales and stock report: " + e.getMessage());
            return;
        }
    }
    
    public static void expirationWarning() {
        String query = Queries.EXPIRATION_WARNIG;
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                String productName = rs.getString("name");
                String expiryDate = rs.getString("expiry_date");
                System.out.println("Warning: Product " + productName + " expires on " + expiryDate);
            }
    
        } catch (SQLException e) {
            System.out.println("Error fetching expiration warnings: " + e.getMessage());
            return;
        }
    }
    
}

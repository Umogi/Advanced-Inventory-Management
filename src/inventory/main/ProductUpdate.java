package inventory.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import inventory.database.DatabaseConnection;
import inventory.database.Queries;

public class ProductUpdate {

    public static boolean updateProductName(int id, String newName) {
        return executeUpdate(Queries.UPDATE_PRODUCT_NAME, newName, id);
    }

    public static boolean updateProductQuantity(int id, int newQuantity) {
        return executeUpdate(Queries.UPDATE_PRODUCT_QUANTITY, newQuantity, id);
    }

    public static boolean updateProductPrice(int id, double newPrice) {
        return executeUpdate(Queries.UPDATE_PRODUCT_PRICE, newPrice, id);
    }

    public static boolean updateProductSupplier(int id, String newSupplier) {
        return executeUpdate(Queries.UPDATE_PRODUCT_SUPPLIER, newSupplier, id);
    }

    public static boolean updateProductExpiryDate(int id, String newExpiryDate) {
        return executeUpdate(Queries.UPDATE_PRODUCT_EXPIRY_DATE, newExpiryDate, id);
    }

    private static boolean executeUpdate(String query, Object value, int id) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setObject(1, value);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Returns true if update was successful

        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }
}

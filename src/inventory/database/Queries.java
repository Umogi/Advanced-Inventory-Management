package inventory.database;

public class Queries {

    public static final String CREATE_USERS_TABLE =
        "CREATE TABLE IF NOT EXISTS Users ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "username TEXT NOT NULL, "
        + "password TEXT NOT NULL, "
        + "role TEXT NOT NULL CHECK(role IN ('admin', 'user'))"
        + ");";

    public static final String CREATE_PRODUCTS_TABLE =
        "CREATE TABLE IF NOT EXISTS Products ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "name TEXT NOT NULL, "
        + "quantity INTEGER NOT NULL, "
        + "price REAL NOT NULL, "
        + "supplier TEXT, "
        + "expiry_date TEXT"
        + ");";

    public static final String CREATE_SALES_TABLE =
        "CREATE TABLE IF NOT EXISTS Sales ("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "product_id INTEGER, "
        + "quantity_sold INTEGER, "
        + "sale_date TEXT, "
        + "total_price REAL, "
        + "FOREIGN KEY (product_id) REFERENCES Products(id)"
        + ");";

    public static final String CHECK_USER_CREDENTIALS = 
        "SELECT * FROM Users WHERE username = ? AND password = ?";
     
    public static final String CREATE_USER = 
        "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
    
    public static final String GET_USERS_ROLE = 
        "SELECT role FROM Users WHERE username = ?";

    public static final String SEARCH_PRODUCT_BY_NAME = 
        "SELECT * FROM Products WHERE name LIKE ?";

    public static final String SEARCH_PRICE_BY_ID = 
        "SELECT price FROM Products WHERE id = ?";
    
    public static final String UPDATE_PRODUCT_NAME = 
        "UPDATE Products SET name = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_QUANTITY = 
        "UPDATE Products SET quantity = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_PRICE = 
        "UPDATE Products SET price = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_SUPPLIER = 
        "UPDATE Products SET supplier = ? WHERE id = ?";

    public static final String UPDATE_PRODUCT_EXPIRY_DATE = 
        "UPDATE Products SET expiry_date = ? WHERE id = ?";
    
    public static final String DELETE_PRODUCT = 
        "DELETE FROM Products WHERE id = ?";

    public static final String DELETE_USER = 
        "DELETE FROM Users WHERE id = ?";
    
    public static final String INSERT_SALE = 
        "INSERT INTO Sales (product_id, quantity_sold, sale_date, total_price) VALUES (?, ?, ?, ?)";

    public static final String INSERT_PRODUCT = 
        "INSERT INTO Products ('name', 'quantity', 'price', 'supplier', 'expiry_date') VALUES (?, ?, ?, ?, ?)";

    public static final String TOTAL_PRODUCTS_SOLD_REPORT = 
        "SELECT p.name, SUM(s.quantity_sold) AS total_sold " +
        "FROM Sales s " +
        "JOIN Products p ON s.product_id = p.id " +
        "GROUP BY p.id;";

    public static final String TOTAL_REVENUE_REPORT = 
        "SELECT SUM(s.total_price) AS total_revenue FROM Sales s;";

    public static final String SALE_AND_STOCK_REPORT =
        "SELECT p.name, SUM(s.quantity_sold) AS total_sold, " +
        "(p.quantity - SUM(s.quantity_sold)) AS remaining_stock " +
        "FROM Products p " +
        "LEFT JOIN Sales s ON p.id = s.product_id " +
        "GROUP BY p.id;";

    public static final String EXPIRATION_WARNIG = 
        "SELECT p.name, p.expiry_date " +
        "FROM Products p " +
        "WHERE p.expiry_date IS NOT NULL " +
        "AND DATE(p.expiry_date) <= DATE('now', '+30 days');";
}

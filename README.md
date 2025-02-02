# Inventory Management System

A Java-based Inventory Management System with `SQLite` database integration. It includes authentication, product management, and sales tracking features, designed for both admin and standard users.

---

## Features

 - **User Authentication:** Admin/User login system  
 - **Product Management:** Add, update, delete, and search for products  
 - **Sales Management:** Record transactions and track revenue  
 - **Reports Generation:** Stock levels, sales trends, and revenue insights  
 - **SQLite Database Integration:** Efficient and lightweight storage  

---

## Project Structure

```
inventory/
│── main/                  # Core application logic
│   ├── Main.java          # Entry point of the application
│   ├── UserInterface.java # User-side operations
│   ├── AdminInterface.java# Admin functionalities
│   ├── Sales.java         # Sales processing logic
│── database/              # Database-related operations
│   ├── DatabaseConnection.java # Manages SQLite connection
│   ├── Queries.java       # SQL queries for database interactions
│   ├── Authentication.java# Handles login validation
│── resources/
│   ├── database.db        # SQLite Database (auto-generated)
│── lib/
│   ├── sqlite-jdbc-3.48.0.0.jar # SQLite JDBC driver
```

---

##  Setup & Execution

###  **Compile the Project**
1.
```
javac -d bin -cp src src/inventory/main/Main.java
```

### **Run the Application**
2.
```
java -cp "bin;lib/sqlite-jdbc-3.48.0.0.jar" inventory.main.Main
```

---

## **Database Schema Overview**

The system consists of three main tables:

### Users Table
| Column   | Type    | Description                |
|----------|--------|----------------------------|
| id       | INT    | Primary Key (Auto Increment) |
| username | TEXT   | Unique Username             |
| password | TEXT   | Hashed Password             |
| role     | TEXT   | Role (admin/user)           |

### Products Table
| Column      | Type   | Description                |
|------------|--------|----------------------------|
| id         | INT    | Primary Key (Auto Increment) |
| name       | TEXT   | Product Name               |
| quantity   | INT    | Available Stock            |
| price      | REAL   | Price per Unit             |
| supplier   | TEXT   | Supplier Name (Optional)   |
| expiry_date| TEXT   | Expiry Date (Optional)     |

### Sales Table
| Column       | Type  | Description                |
|-------------|-------|----------------------------|
| id          | INT   | Primary Key (Auto Increment) |
| product_id  | INT   | Foreign Key (Products Table) |
| quantity_sold | INT  | Quantity Sold              |
| sale_date   | TEXT  | Date of Sale               |
| total_price | REAL  | Total Sale Amount          |

---

## **Functionality Breakdown**

### **Authentication System**
- Users are authenticated via `Authentication.checkUserCredentials(username, password)`.
- Admins have full control over products, sales, and users.
- Standard users can view and purchase products.

### **Product Management**
- Add new products via `AdminInterface.addProduct()`
- Update existing products via `ProductUpdate.updateProductName()`, `updateProductPrice()`, etc.
- Delete unwanted products using `AdminInterface.deleteProduct()`.

### **Sales Processing**
- Users input product ID, quantity, and date.
- The system fetches the product price and calculates the total.
- Sales data is recorded using `INSERT INTO Sales (...) VALUES (...)`.

### **Reporting System**
- Generate reports using:
  - **Total Products Sold:** `Queries.TOTAL_PRODUCTS_SOLD_REPORT`
  - **Total Revenue:** `Queries.TOTAL_REVENUE_REPORT`
  - **Stock & Sales Overview:** `Queries.SALE_AND_STOCK_REPORT`
  - **Expiring Soon Products:** `Queries.EXPIRATION_WARNIG`

---

## **Example Queries**
```
-- Retrieve all products
SELECT * FROM Products;

-- Search for a product by name
SELECT * FROM Products WHERE name LIKE '%Laptop%';

-- Update a product's price
UPDATE Products SET price = 999.99 WHERE id = 1;

-- Record a sale
INSERT INTO Sales (product_id, quantity_sold, sale_date, total_price) 
VALUES (1, 2, '2025-02-02', 1999.98);
```

---

## **How to Use**

1. **Run the program and log in**
   - If credentials match, you enter either Admin or User mode.

2. **Admin Capabilities**
   - Add, update, and delete products
   - View stock reports and sales analytics
   - Manage user accounts

3. **User Capabilities**
   - Search for products
   - Purchase and record sales transactions

---

## License

This project is open-source and available under the MIT [LICENSE](LICENSE).

**Developed by [[Umogi](https://github.com/Umogi)]**

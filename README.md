# Advanced-Inventory-Management
----
## The Idea

The main project is to make the a Page that you can log In and make changes as a Staff Or an Admin.

Basically the whole project is going to have 

- User Authentication (Admin vs. Staff)
- Database Integration (Instead of just an ArrayList)
- Stock Threshold Alerts (Warn when stock is low)
- Sales Management (Track purchases and update stock)
- Reports & Analytics (Show sales trends)
---
## Analytic Features

1. User Authentication
	- Users must log in before accessing the inventory.
	- Two roles
	    - Admin (can addremove users, view reports, modify inventory)
	    - Staff (can add sales, update stock but not delete items)
2. Product Management
	- Each product has
	    - ID, Name, Quantity, Price, Supplier Info, Expiry Date
	- Admins can adddeletemodify products.
	- Staff can only update quantity when a sale is made.
3. Sales Processing
	- Each time a product is sold, it reduces stock.
	- If stock falls below a threshold, an alert is triggered.
4. Stock Threshold Alerts
	- If a product’s quantity goes below a certain level, send an alert.
	- Example Warning! Low stock for item Laptop (Only 2 left).
5. Reports & Analytics
	-  Generate reports for
        - Total sales in a day, week, or month.
        - Most sold items.
        - Low stock items.
        - Revenue generated.
6. Database Integration
	- Store products, users, and sales data in MySQL or SQLite.
	- Use JDBC (Java Database Connectivity) to interact with the database.
---
## Development So Far
 - Connection to the sql
 - Creates Tables
 - Queries for Login and Sees for Admin or User

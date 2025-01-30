-- Users table
CREATE TABLE IF NOT EXISTS Users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    role ENUM('admin', 'user') NOT NULL
);

-- Products table
CREATE TABLE IF NOT EXISTS Products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    price REAL NOT NULL,
    supplier TEXT,
    expiry_date TEXT
);

-- Sales table
CREATE TABLE IF NOT EXISTS Sales (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER,
    quantity_sold INTEGER,
    sale_date TEXT,
    total_price REAL,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Samples
INSERT INTO Users (username, password, role) VALUES ('admin', 'admin123', 'admin');
INSERT INTO Users (username, password, role) VALUES ('user', 'user123', 'user');
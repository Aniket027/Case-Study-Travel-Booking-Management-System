# Case-Study-Travel-Booking-Management-System
## Introduction

This project is a menu-based console application developed in Core Java to manage travel bookings. It includes functionalities for managing destinations, customers, and bookings, with data stored in a MySQL database and accessed via JDBC.

## Features

- **Destination Management:**
  - Add a new destination
  - View destination details
  - Update destination information
  - Delete a destination
- **Customer Management:**
  - Add a new customer
  - View customer details
  - Update customer information
  - Delete a customer
- **Booking Management:**
  - Create a new booking
  - View booking details
  - Update booking information
  - Cancel a booking

## Database Schema

### Destination Table:

- `destination_id` (Primary Key)
- `name`
- `location`
- `description`
- `price_per_day`

### Customer Table:

- `customer_id` (Primary Key)
- `name`
- `email`
- `phone_number`
- `address`

### Booking Table:

- `booking_id` (Primary Key)
- `destination_id` (Foreign Key references Destination Table)
- `customer_id` (Foreign Key references Customer Table)
- `booking_date`
- `start_date`
- `end_date`
- `total_cost`
- `status` (confirmed/cancelled)

## Prerequisites

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (JDBC Driver)

## Setup Instructions

### Step 1: Set Up the MySQL Database

1. Start the MySQL server.
2. Create the database and tables using the following SQL commands:

   ```sql
   CREATE DATABASE travelbooking;

   USE travelbooking;

   CREATE TABLE Destination (
       destination_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       location VARCHAR(255) NOT NULL,
       description VARCHAR(255),
       price_per_day DECIMAL(10, 2) NOT NULL
   );

   CREATE TABLE Customer (
       customer_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL,
       phone_number VARCHAR(20),
       address VARCHAR(255);
   );

   CREATE TABLE Booking (
       booking_id INT AUTO_INCREMENT PRIMARY KEY,
       destination_id INT,
       customer_id INT,
       booking_date DATE,
       start_date DATE,
       end_date DATE,
       total_cost DECIMAL(10, 2),
       status VARCHAR(50),
       FOREIGN KEY (destination_id) REFERENCES Destination(destination_id),
       FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
   );
   ```

### Step 2: Configure the Project

1. Download and extract the MySQL Connector/J (JDBC driver) from [MySQL Connector/J Download](https://dev.mysql.com/downloads/connector/j/).
2. Place the `mysql-connector-java-x.x.x.jar` file in your project directory.

### Step 3: Update DBConnectionUtil.java

Edit `com/travelbooking/util/DBConnectionUtil.java` with your MySQL database credentials:

    ```java
    package com.travelbooking.util;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DBConnectionUtil {
        private static final String URL = "jdbc:mysql://localhost:3306/travelbooking";
        private static final String USER = "your_username";
        private static final String PASSWORD = "your_password";

        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
    ```

Replace `your_username` and `your_password` with your actual MySQL username and password.

### Running the Project on Windows

1. Open a Command Prompt or PowerShell window.
2. Navigate to your project directory.
3. Compile the Java files, including the MySQL Connector/J in the classpath:

   ```sh
   javac -cp .;path\to\mysql-connector-java-x.x.x.jar com\travelbooking\*.java com\travelbooking\dao\*.java com\travelbooking\model\*.java com\travelbooking\service\*.java com\travelbooking\util\*.java
   ```

   Replace `path\to\mysql-connector-java-x.x.x.jar` with the actual path to your MySQL Connector/J JAR file.

4. Run the main application:

   ```sh
   java -cp .;path\to\mysql-connector-java-x.x.x.jar Main
   ```

## Usage Instructions

1. After running the application, the main menu will be displayed with options for managing destinations, customers, and bookings.
2. Choose an option by entering the corresponding number.
3. Follow the prompts to add, view, update, or delete destinations, customers, or bookings.

## Code Structure

- **com.travelbooking.dao**: Data Access Objects (DAO) for database interactions
- **com.travelbooking.model**: Models representing the database entities
- **com.travelbooking.service**: Services containing business logic
- **com.travelbooking.util**: Utility classes, including database connection utility
- **Main.java**: The main entry point of the application

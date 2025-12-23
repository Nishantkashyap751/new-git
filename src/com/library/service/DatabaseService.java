package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

public class DatabaseService implements DataStorageService {
    // MySQL Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "7988";
    
    private Connection connection;

    public DatabaseService() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Create tables if they don't exist
            createTablesIfNotExist();
            System.out.println("MySQL Database connected successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            System.err.println("Make sure mysql-connector-java.jar is in the lib folder.");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            System.err.println("Make sure MySQL is running and database 'library_db' exists.");
            System.err.println("Or create database with: CREATE DATABASE library_db;");
            System.exit(1);
        }
    }

    private void createTablesIfNotExist() {
        try (Statement stmt = connection.createStatement()) {
            // Create books table
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "bookId VARCHAR(50) PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "status VARCHAR(50) NOT NULL)");

            // Create members table
            stmt.execute("CREATE TABLE IF NOT EXISTS members (" +
                    "memberId VARCHAR(50) PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL)");

            // Create transactions table
            stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "transactionId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "bookId VARCHAR(50) NOT NULL, " +
                    "memberId VARCHAR(50) NOT NULL, " +
                    "borrowDate VARCHAR(50) NOT NULL, " +
                    "returnDate VARCHAR(50), " +
                    "FOREIGN KEY(bookId) REFERENCES books(bookId), " +
                    "FOREIGN KEY(memberId) REFERENCES members(memberId))");

            System.out.println("Tables verified/created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    // --- READ OPERATIONS ---

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                members.add(new Member(
                        rs.getString("memberId"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }
        return members;
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String returnDate = rs.getString("returnDate");
                returnDate = (returnDate == null || returnDate.isEmpty()) ? "" : returnDate;
                transactions.add(new Transaction(
                        rs.getString("bookId"),
                        rs.getString("memberId"),
                        rs.getString("borrowDate"),
                        returnDate
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    // --- WRITE OPERATIONS ---

    public void saveBooks(List<Book> books) {
        try {
            // Clear existing data
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                stmt.execute("DELETE FROM books");
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");
            }

            // Insert all books
            String insertQuery = "INSERT INTO books (bookId, title, author, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                for (Book book : books) {
                    pstmt.setString(1, book.getBookId());
                    pstmt.setString(2, book.getTitle());
                    pstmt.setString(3, book.getAuthor());
                    pstmt.setString(4, book.getStatus());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            System.out.println("Books data saved to MySQL.");
        } catch (SQLException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    public void saveMembers(List<Member> members) {
        try {
            // Clear existing data
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                stmt.execute("DELETE FROM members");
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");
            }

            // Insert all members
            String insertQuery = "INSERT INTO members (memberId, name, email) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                for (Member member : members) {
                    pstmt.setString(1, member.getMemberId());
                    pstmt.setString(2, member.getName());
                    pstmt.setString(3, member.getEmail());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            System.out.println("Members data saved to MySQL.");
        } catch (SQLException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    public void saveTransactions(List<Transaction> transactions) {
        try {
            // Clear existing data
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("DELETE FROM transactions");
            }

            // Insert all transactions
            String insertQuery = "INSERT INTO transactions (bookId, memberId, borrowDate, returnDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                for (Transaction transaction : transactions) {
                    pstmt.setString(1, transaction.getBookId());
                    pstmt.setString(2, transaction.getMemberId());
                    pstmt.setString(3, transaction.getBorrowDate());
                    pstmt.setString(4, transaction.getReturnDate().isEmpty() ? null : transaction.getReturnDate());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            System.out.println("Transactions data saved to MySQL.");
        } catch (SQLException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("MySQL connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}

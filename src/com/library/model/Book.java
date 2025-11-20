package com.library.model;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String status; // e.g., "Available" or "Issued"

    // Constructor
    public Book(String bookId, String title, String author, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    // Getters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getStatus() { return status; }

    // Setter (used for updating status on borrow/return)
    public void setStatus(String status) { this.status = status; }

    // Method to convert object to CSV line
    public String toCsvLine() {
        return bookId + "," + title + "," + author + "," + status;
    }

    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Status: " + status;
    }
}
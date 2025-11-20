package com.library.model;

// Note: Using simple String for dates for beginner level
public class Transaction {
    private String bookId;
    private String memberId;
    private String borrowDate;
    private String returnDate; // Will be null/empty for currently borrowed books

    // Constructor for new borrow
    public Transaction(String bookId, String memberId, String borrowDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = ""; // Empty string for no return date yet
    }

    // Constructor for loading from file
    public Transaction(String bookId, String memberId, String borrowDate, String returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters
    public String getBookId() { return bookId; }
    public String getMemberId() { return memberId; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }

    // Setter (used when a book is returned)
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    // Method to convert object to CSV line
    public String toCsvLine() {
        return bookId + "," + memberId + "," + borrowDate + "," + returnDate;
    }

    @Override
    public String toString() {
        String status = returnDate.isEmpty() ? " (ISSUED)" : " (RETURNED)";
        return "Book ID: " + bookId + ", Member ID: " + memberId + ", Borrow Date: " + borrowDate + ", Return Date: " + (returnDate.isEmpty() ? "N/A" : returnDate) + status;
    }
}
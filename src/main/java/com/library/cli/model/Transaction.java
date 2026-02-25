package com.library.cli.model;

public class Transaction {
    private String bookId;
    private String memberId;
    private String borrowDate;
    private String returnDate;

    public Transaction(String bookId, String memberId, String borrowDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = "";
    }

    public Transaction(String bookId, String memberId, String borrowDate, String returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String toCsvLine() {
        return bookId + "," + memberId + "," + borrowDate + "," + returnDate;
    }

    @Override
    public String toString() {
        String status = returnDate.isEmpty() ? " (ISSUED)" : " (RETURNED)";
        return "Book ID: " + bookId + ", Member ID: " + memberId + ", Borrow Date: " + borrowDate + ", Return Date: "
                + (returnDate.isEmpty() ? "N/A" : returnDate) + status;
    }
}

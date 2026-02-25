package com.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "borrow_date", nullable = false)
    private String borrowDate;

    @Column(name = "return_date")
    private String returnDate;

    public Transaction() {
    }

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

    public Long getId() {
        return id;
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
        return returnDate != null ? returnDate : "";
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}

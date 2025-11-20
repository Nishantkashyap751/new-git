package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate; // Using a simple way to get today's date

public class LibraryService {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private final FileService fileService;

    public LibraryService() {
        this.fileService = new FileService();
        // Load data on startup
        this.books = fileService.loadBooks();
        this.members = fileService.loadMembers();
        this.transactions = fileService.loadTransactions();
    }

    // --- Book Management ---

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
    
    // --- Member Management ---
    
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    // --- Transaction (Borrow/Return) Management ---

    public boolean borrowBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return false;
        }
        if (member == null) {
            System.out.println("Error: Member with ID " + memberId + " not found.");
            return false;
        }

        if (book.getStatus().equals("Available")) {
            // 1. Update book status
            book.setStatus("Issued");
            // 2. Create new transaction record
            String todayDate = LocalDate.now().toString();
            Transaction transaction = new Transaction(bookId, memberId, todayDate);
            transactions.add(transaction);
            System.out.println("Successfully borrowed: '" + book.getTitle() + "' by " + member.getName());
            return true;
        } else {
            System.out.println("Error: Book '" + book.getTitle() + "' is currently " + book.getStatus() + ".");
            return false;
        }
    }

    public boolean returnBook(String bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return false;
        }

        if (book.getStatus().equals("Issued")) {
            // 1. Find the active transaction (returnDate is empty)
            for (Transaction t : transactions) {
                if (t.getBookId().equals(bookId) && t.getReturnDate().isEmpty()) {
                    // 2. Update transaction return date
                    t.setReturnDate(LocalDate.now().toString());
                    // 3. Update book status
                    book.setStatus("Available");
                    System.out.println("Successfully returned: '" + book.getTitle() + "'");
                    return true;
                }
            }
            System.out.println("Error: Book status is 'Issued' but no active transaction found. Data mismatch.");
            return false;
        } else {
            System.out.println("Error: Book '" + book.getTitle() + "' is not currently Issued (Status: " + book.getStatus() + ").");
            return false;
        }
    }

    // Getter for the collections (used by ReportService)
    public List<Book> getBooks() {
        return books;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // --- Save Data ---
    public void saveData() {
        fileService.saveBooks(books);
        fileService.saveMembers(members);
        fileService.saveTransactions(transactions);
    }
}
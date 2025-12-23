package com.library.service;

import java.time.LocalDate;
import java.util.List;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction; 

public class LibraryService {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private final DataStorageService storageService;

    /**
     * Constructor - Uses MySQL Database with JDBC
     */
    public LibraryService() {
        this.storageService = new DatabaseService();
        this.books = storageService.loadBooks();
        this.members = storageService.loadMembers();
        this.transactions = storageService.loadTransactions();
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
            for (Transaction t : transactions) {
                if (t.getBookId().equals(bookId) && t.getReturnDate().isEmpty()) {
                    t.setReturnDate(LocalDate.now().toString());
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

    public List<Book> getBooks() {
        return books;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // --- Save Data ---
    public void saveData() {
        storageService.saveBooks(books);
        storageService.saveMembers(members);
        storageService.saveTransactions(transactions);
    }

    // Close storage connection
    public void closeStorage() {
        storageService.closeConnection();
    }
}
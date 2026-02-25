package com.library.service;
import java.util.List;
import java.util.stream.Collectors;

import com.library.model.Book;
public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    
    public void generateIssuedBooksReport() {
        System.out.println("\n--- Issued Books Report ---");
        
        List<Book> issuedBooks = libraryService.getBooks().stream()
                // Stream API: Filter to only keep books where status is "Issued"
                .filter(book -> "Issued".equals(book.getStatus()))
                .collect(Collectors.toList());

        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }

        System.out.println("Total Issued Books: " + issuedBooks.size());
        for (Book book : issuedBooks) {
            System.out.println(book);
        }
        
        System.out.println("\n--- Active Transactions ---");
        libraryService.getTransactions().stream()
                // Filter to only keep transactions that haven't been returned (returnDate is empty)
                .filter(transaction -> transaction.getReturnDate().isEmpty())
                .forEach(System.out::println);
    }
}
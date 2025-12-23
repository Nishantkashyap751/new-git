package com.library.service;

import java.util.List;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

/**
 * Interface for data storage operations.
 * Allows switching between FileService (CSV) and DatabaseService (JDBC/SQLite)
 */
public interface DataStorageService {
    List<Book> loadBooks();
    List<Member> loadMembers();
    List<Transaction> loadTransactions();
    
    void saveBooks(List<Book> books);
    void saveMembers(List<Member> members);
    void saveTransactions(List<Transaction> transactions);
    
    void closeConnection();
}

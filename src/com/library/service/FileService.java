package com.library.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

public class FileService implements DataStorageService {

    private static final String BOOKS_FILE = "book.csv";
    private static final String MEMBERS_FILE = "member.csv";
    private static final String TRANSACTIONS_FILE = "transaction.csv";

    // --- READ OPERATIONS ---

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    books.add(new Book(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing books file found. Starting with empty book list.");
        } catch (IOException e) {
            System.err.println("Error reading books file: " + e.getMessage());
        }
        return books;
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    members.add(new Member(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
             System.out.println("No existing members file found. Starting with empty member list.");
        } catch (IOException e) {
            System.err.println("Error reading members file: " + e.getMessage());
        }
        return members;
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String returnDate = parts.length == 4 ? parts[3] : "";
                    transactions.add(new Transaction(parts[0], parts[1], parts[2], returnDate));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing transactions file found. Starting with empty transaction list.");
        } catch (IOException e) {
            System.err.println("Error reading transactions file: " + e.getMessage());
        }
        return transactions;
    }

    // --- WRITE OPERATIONS (File Writing) ---

    public void saveBooks(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                pw.println(book.toCsvLine());
            }
            System.out.println("Books data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing books file: " + e.getMessage());
        }
    }

    public void saveMembers(List<Member> members) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                pw.println(member.toCsvLine());
            }
            System.out.println("Members data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing members file: " + e.getMessage());
        }
    }

    public void saveTransactions(List<Transaction> transactions) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TRANSACTIONS_FILE))) {
            for (Transaction transaction : transactions) {
                pw.println(transaction.toCsvLine());
            }
            System.out.println("Transactions data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing transactions file: " + e.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        // No connection to close for file-based storage
        System.out.println("File storage closed.");
    }
}
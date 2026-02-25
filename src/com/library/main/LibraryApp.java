package com.library.main;

import java.util.Scanner;

import com.library.model.Book;
import com.library.model.Member;
import com.library.service.LibraryService;
import com.library.service.ReportService;

public class LibraryApp {
    private static LibraryService libraryService;
    private static ReportService reportService;
    private static Scanner scanner;

    public static void main(String[] args) {
        libraryService = new LibraryService();
        reportService = new ReportService(libraryService);
        scanner = new Scanner(System.in);

        System.out.println("--- Welcome to the Beginner Library Management System ---");

        int choice;
        do {
            displayMenu();
            // Basic Exception Handling for non-integer input
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                processChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1; // Set to a non-exit value
            }
        } while (choice != 0);

        // Save data on exit
        libraryService.saveData();
        System.out.println("Application closed. Data saved.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add New Book");
        System.out.println("2. Register New Member");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Generate Issued Books Report");
        System.out.println("0. Exit and Save Data");
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                addMember();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                reportService.generateIssuedBooksReport();
                break;
            case 0:

                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addBook() {
        System.out.println("\n--- Add Book ---");
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        // Default status for a new book is "Available"
        Book newBook = new Book(id, title, author, "Available");
        libraryService.addBook(newBook);
    }

    private static void addMember() {
        System.out.println("\n--- Register Member ---");
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Member newMember = new Member(id, name, email);
        libraryService.addMember(newMember);
    }

    private static void borrowBook() {
        System.out.println("\n--- Borrow Book ---");
        System.out.print("Enter Book ID to borrow: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        libraryService.borrowBook(bookId, memberId);
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();

        libraryService.returnBook(bookId);
    }
}
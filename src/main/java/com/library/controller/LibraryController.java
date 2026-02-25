package com.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.service.LibraryService;
import com.library.service.ReportService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LibraryController {

    private final LibraryService libraryService;
    private final ReportService reportService;

    public LibraryController(LibraryService libraryService, ReportService reportService) {
        this.libraryService = libraryService;
        this.reportService = reportService;
    }

    // ── Books ────────────────────────────────────────────────────────────────

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<Map<String, String>> addBook(@RequestBody Book book) {
        String result = libraryService.addBook(book);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(Map.of("message", result));
        }
        return ResponseEntity.ok(Map.of("message", result));
    }

    // ── Members ──────────────────────────────────────────────────────────────

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(libraryService.getMembers());
    }

    @PostMapping("/members")
    public ResponseEntity<Map<String, String>> addMember(@RequestBody Member member) {
        String result = libraryService.addMember(member);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(Map.of("message", result));
        }
        return ResponseEntity.ok(Map.of("message", result));
    }

    // ── Borrow / Return ──────────────────────────────────────────────────────

    @PostMapping("/borrow")
    public ResponseEntity<Map<String, String>> borrowBook(@RequestBody Map<String, String> req) {
        String bookId = req.get("bookId");
        String memberId = req.get("memberId");
        String result = libraryService.borrowBook(bookId, memberId);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(Map.of("message", result));
        }
        return ResponseEntity.ok(Map.of("message", result));
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<Map<String, String>> returnBook(@PathVariable String bookId) {
        String result = libraryService.returnBook(bookId);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(Map.of("message", result));
        }
        return ResponseEntity.ok(Map.of("message", result));
    }

    // ── Report ───────────────────────────────────────────────────────────────

    @GetMapping("/report")
    public ResponseEntity<List<Book>> getReport() {
        return ResponseEntity.ok(reportService.getIssuedBooks());
    }

    // ── Transactions ─────────────────────────────────────────────────────────

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(libraryService.getTransactions());
    }
}

package com.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.model.Book;

@Service
public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public List<Book> getIssuedBooks() {
        return libraryService.getBooks().stream()
                .filter(book -> "Issued".equals(book.getStatus()))
                .collect(Collectors.toList());
    }
}

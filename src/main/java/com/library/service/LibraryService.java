package com.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;

import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    public LibraryService(BookRepository bookRepository,
            MemberRepository memberRepository,
            TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }

    // ── Book Management ─────────────────────────────────────────────────────

    public String addBook(Book book) {
        if (bookRepository.existsById(book.getBookId())) {
            return "Error: Book ID " + book.getBookId() + " already exists.";
        }
        book.setStatus("Available");
        bookRepository.save(book);
        return "Book added: " + book.getTitle();
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(String bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    // ── Member Management ────────────────────────────────────────────────────

    public String addMember(Member member) {
        if (memberRepository.existsById(member.getMemberId())) {
            return "Error: Member ID " + member.getMemberId() + " already exists.";
        }
        memberRepository.save(member);
        return "Member registered: " + member.getName();
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    // ── Borrow / Return ──────────────────────────────────────────────────────

    public String borrowBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null)
            return "Error: Book ID " + bookId + " not found.";
        if (member == null)
            return "Error: Member ID " + memberId + " not found.";

        if ("Available".equals(book.getStatus())) {
            book.setStatus("Issued");
            bookRepository.save(book);
            Transaction transaction = new Transaction(bookId, memberId, LocalDate.now().toString());
            transactionRepository.save(transaction);
            return "Successfully borrowed: '" + book.getTitle() + "' by " + member.getName();
        } else {
            return "Error: Book '" + book.getTitle() + "' is currently " + book.getStatus() + ".";
        }
    }

    public String returnBook(String bookId) {
        Book book = findBookById(bookId);
        if (book == null)
            return "Error: Book ID " + bookId + " not found.";

        if ("Issued".equals(book.getStatus())) {
            Optional<Transaction> txOpt = transactionRepository.findByBookIdAndReturnDate(bookId, "");
            if (txOpt.isPresent()) {
                Transaction tx = txOpt.get();
                tx.setReturnDate(LocalDate.now().toString());
                transactionRepository.save(tx);
                book.setStatus("Available");
                bookRepository.save(book);
                return "Successfully returned: '" + book.getTitle() + "'";
            }
            return "Error: Data mismatch - no active transaction found for this book.";
        } else {
            return "Error: Book '" + book.getTitle() + "' is not currently Issued (Status: " + book.getStatus() + ").";
        }
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}

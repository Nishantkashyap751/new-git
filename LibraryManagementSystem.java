class Book {
    public String title;
    public String author;
    public String genre;
    public int bookID;
    public String availabilityStatus;

    public Book(String title, String author, String genre, int bookID, String availabilityStatus) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Status: " + availabilityStatus;
    }
}

class BookNode {
    public Book data;
    public BookNode next;
    public BookNode prev; 

    public BookNode(Book data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class LibraryManager {
    public BookNode head;
    public BookNode tail;

    public LibraryManager() {
        this.head = null;
        this.tail = null;
    }

    // --- Core DLL Operations ---

    public void addAtBeginning(Book newBook) {
        BookNode newNode = new BookNode(newBook);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        System.out.println(" Added to Start: " + newBook.title);
    }

    public void addAtEnd(Book newBook) {
        BookNode newNode = new BookNode(newBook);

        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        System.out.println(" Added to End: " + newBook.title);
    }

    public void displayForward() {
        BookNode current = this.head;
        if (current == null) {
            System.out.println("The library is empty.");
            return;
        }

        System.out.println("\n--- Library Books (Forward) ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("--------------------------------\n");
    }

    public void displayReverse() {
        BookNode current = this.tail;
        if (current == null) {
            System.out.println("The library is empty.");
            return;
        }

        System.out.println("\n--- Library Books (Reverse) ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.prev; 
        }
        System.out.println("--------------------------------\n");
    }
    // --- Additional Required Operation ---
    public void countTotalBooks() {
        int count = 0;
        BookNode current = this.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        System.out.println(" Total number of books in the library: " + count);
    }

    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();

        Book b1 = new Book("interstellar", "Christopher Nolan", "Sci-Fi", 101, "Available");
        Book b2 = new Book("1984", "George Orwell", "Dystopian", 102, "Available");
        Book b3 = new Book("The Martian", "Andy Weir", "Sci-Fi", 103, "Checked Out");
        
        manager.addAtEnd(b1);
        manager.addAtEnd(b3);
        manager.addAtBeginning(b2); 
        
        manager.displayForward();
        manager.displayReverse();
        manager.countTotalBooks();
    }
}
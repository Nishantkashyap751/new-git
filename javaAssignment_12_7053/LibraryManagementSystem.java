abstract class LibraryItem {
    private int itemId;
    private String title;
    private String author;

    public LibraryItem(int itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public abstract int getLoanDuration();

    public void getItemDetails() {
        System.out.println("ID: " + itemId + ", Title: " + title + ", Author: " + author);
    }
}

interface Reservable {
    void reserveItem(String borrower);
    boolean checkAvailability();
}

class Book extends LibraryItem implements Reservable {
    private boolean reserved;
    private String borrower;

    public Book(int itemId, String title, String author) {
        super(itemId, title, author);
    }

    public int getLoanDuration() {
        return 14;
    }

    public void reserveItem(String borrower) {
        this.borrower = borrower;
        reserved = true;
    }

    public boolean checkAvailability() {
        return !reserved;
    }
}

class Magazine extends LibraryItem implements Reservable {
    private boolean reserved;
    private String borrower;

    public Magazine(int itemId, String title, String author) {
        super(itemId, title, author);
    }

    public int getLoanDuration() {
        return 7;
    }

    public void reserveItem(String borrower) {
        this.borrower = borrower;
        reserved = true;
    }

    public boolean checkAvailability() {
        return !reserved;
    }
}

class DVD extends LibraryItem implements Reservable {
    private boolean reserved;
    private String borrower;

    public DVD(int itemId, String title, String author) {
        super(itemId, title, author);
    }

    public int getLoanDuration() {
        return 3;
    }

    public void reserveItem(String borrower) {
        this.borrower = borrower;
        reserved = true;
    }

    public boolean checkAvailability() {
        return !reserved;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryItem i1 = new Book(1, "Java Basics", "Author A");
        LibraryItem i2 = new Magazine(2, "Tech World", "Author B");
        LibraryItem i3 = new DVD(3, "Movie XYZ", "Director C");

        LibraryItem[] items = {i1, i2, i3};

        for (LibraryItem item : items) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            if (item instanceof Reservable) {
                Reservable r = (Reservable) item;
                System.out.println("Available: " + r.checkAvailability());
                r.reserveItem("User1");
                System.out.println("Available after reservation: " + r.checkAvailability());
            }
            System.out.println();
        }
    }
}

class Movie {
    public String title;
    public String director;
    public int year;
    public double rating;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Director: " + director + ", Year: " + year + ", Rating: " + rating;
    }
}

class MovieNode {
    public Movie data;
    public MovieNode next;
    public MovieNode prev;

    public MovieNode(Movie data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class MovieManagementSystem {
    public MovieNode head;
    public MovieNode tail;

    public MovieManagementSystem() {
        this.head = null;
        this.tail = null;
    }

    public void addAtBeginning(Movie newMovie) {
        MovieNode newNode = new MovieNode(newMovie);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        System.out.println(" Added to Start: " + newMovie.title);
    }

    public void addAtEnd(Movie newMovie) {
        MovieNode newNode = new MovieNode(newMovie);

        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        System.out.println(" Added to End: " + newMovie.title);
    }

    public void displayForward() {
        MovieNode current = this.head;
        if (current == null) {
            System.out.println("The movie record list is empty.");
            return;
        }

        System.out.println("\n--- Current Movie Records (Forward) ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("-------------------------------------\n");
    }

    public void displayReverse() {
        MovieNode current = this.tail;
        if (current == null) {
            System.out.println("The movie record list is empty.");
            return;
        }

        System.out.println("\n--- Current Movie Records (Reverse) ---");
        while (current != null) {
            System.out.println(current.data);
            current = current.prev;
        }
        System.out.println("-------------------------------------\n");
    }


    public static void main(String[] args) {
        MovieManagementSystem system = new MovieManagementSystem();

        Movie m1 = new Movie("Interstellar", "Christopher Nolan", 2010, 8.8);
        Movie m2 = new Movie("Iron Man", "Jon Favreau", 2008, 7.9);
        Movie m3 = new Movie("Avatar", "James Cameron", 1999, 8.7);
        
        system.addAtEnd(m1);
        system.addAtEnd(m3);
        
        system.addAtBeginning(m2);
        
        system.displayForward();
        system.displayReverse();
    }
}
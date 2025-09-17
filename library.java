public class library {
    String title;
    String author;
    double price;
    boolean isAvailable;
    void setDetails(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true; 
    }
    void borrow(){
        if(isAvailable){
            System.out.println("You have borrowed the book: " + title);
            isAvailable = false;
        } else {
            System.out.println("Sorry, the book: " + title + " is currently not available.");
        }
    }
    public static void main(String[] args) {
        library book1 = new library();
        book1.setDetails("champak", "nishant kashyap", 9.99);
        book1.borrow(); 
    }
}

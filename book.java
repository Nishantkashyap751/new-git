public class book {
    static String libraryName="Pustaklaya";
    String title,author;
    final int ISBN;
    public static void displayLibraryName(){
        System.out.println("Library name: "+libraryName);
    }
    book(String title,String author,int ISBN){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        if (this instanceof book) {
            displayLibraryName();
            System.out.println("Book title: " + title);
            System.out.println("Author: " + author);
            System.out.println("ISBN number: " + ISBN);
            System.out.println("Library name: " + libraryName);
        } else {
            System.out.println("Object is not an instance of book. Details not displayed.");
        }
    }
    public static void main(String[]args){
        String display;
        book b1=new book("Interstellar","Christopher Nolan",123456);
        book b2=new book("Champak","Gulzar",789012);
        book.displayLibraryName();
        System.out.println("library name: "+libraryName);
}}

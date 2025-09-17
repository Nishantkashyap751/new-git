import java.util.Scanner;
public class book {
    String title;
    String author;
    double price;
    book(){
       System.out.println("Default Constructor");
    }
    public book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

   public static void main(String ar[]){
       Scanner s1=new Scanner (System.in);
              book b=new book();
       System.out.println("Enter the title of the book:");
       String title=s1.nextLine();
       System.out.println("Enter the author of the book:");
       String author=s1.nextLine();
       System.out.println("Enter the price of the book:");
       double price=s1.nextDouble();
       book b1=new book(title,author,price);
       System.out.println("Title: "+b1.title);
       System.out.println("Author: "+b1.author);
       System.out.println("Price: "+b1.price);
   }
}
import java.util.Scanner;
public class book {
    Scanner s1=new Scanner(System.in);
    String title,author;
    double price;
    void get(){
        System.out.print("Enter Title: ");
        title = s1.nextLine();
        System.out.print("Enter Author: ");
        author = s1.nextLine();
        System.out.print("Enter Price: ");
        price = s1.nextDouble();
    }
    void display(){
        System.out.println("Title: "+title);
        System.out.println("Author: "+author);
        System.out.println("Price: "+price);
    }
    public static void main(String[] args) {
        book book = new book();
        book.get();
        book.display();
    }
    
}

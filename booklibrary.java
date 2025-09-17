import java.util.Scanner;
public class booklibrary {
    public String ISBN;
    protected String title;
    private String author;
    Scanner s1=new Scanner(System.in);
    public void get(){
        System.out.println("enter the author name:");
        author=s1.nextLine();
    }
}
 class Ebook extends booklibrary{
    public double size;
    public String format;
    public void setDetails(String ISBN,String title){
        this.ISBN=ISBN;
        this.title=title;
        System.out.println("ISBN: " + ISBN);
        System.out.println("Title: " + title);
    }
    public static void main(String ar[]){
        Ebook e=new Ebook();
        e.get();
        e.setDetails("978","Java Programming");
    }
}

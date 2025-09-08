      import java.util.Scanner;
public class javaPracticeProblem7 {
    Scanner s1=new Scanner(System.in);
    double length,width;
    double total;
    void get(){
        
        System.out.println("enter length:");
        length=s1.nextInt();
        System.out.println("enter time:");
        width=s1.nextInt();
      
      
    }
    void display(){
        total=2*(length+width);
        System.out.println(total);
    }
     public static void main(String ar[]){
        javaPracticeProblem7 p1=new javaPracticeProblem7();
        p1.get();
        p1.display();
    }
}
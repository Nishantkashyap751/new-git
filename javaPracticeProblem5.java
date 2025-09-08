import java.util.Scanner;
public class javaPracticeProblem5 {
    Scanner s1=new Scanner(System.in);
    double radius,height;
    double total;
    void get(){
        System.out.println("enter radius:");
        radius=s1.nextInt();
        System.out.println("enter height:");
        height=s1.nextInt();
      
      
    }
    void display(){
        total=Math.PI*Math.pow(radius,2)*height;
        System.out.println(total);
    }
     public static void main(String ar[]){
        javaPracticeProblem5 p1=new javaPracticeProblem5();
        p1.get();
        p1.display();
    }
}
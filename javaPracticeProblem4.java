import java.util.Scanner;
public class javaPracticeProblem4 {
    Scanner s1=new Scanner(System.in);
    int radius;
    double total;
    void get(){
        System.out.println("enter radius:");
        radius=s1.nextInt();
      
    }
    void display(){
        total=Math.PI*Math.pow(radius,2);
        System.out.println(total);
    }
     public static void main(String ar[]){
        javaPracticeProblem4 p1=new javaPracticeProblem4();
        p1.get();
        p1.display();
    }
}
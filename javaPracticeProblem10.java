import java.util.Scanner;
public class javaPracticeProblem10 {
    Scanner s1=new Scanner(System.in);
    int kilometers;
    double miles;
    void get(){
        System.out.println("enter kilometers:");
        kilometers=s1.nextInt();
      
    }
    void display(){
        miles=kilometers*0.621371;
        System.out.println(miles);
    }
     public static void main(String ar[]){
        javaPracticeProblem10 p1=new javaPracticeProblem10();
        p1.get();
        p1.display();
    }
}
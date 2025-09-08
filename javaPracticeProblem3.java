import java.util.Scanner;
public class javaPracticeProblem3 {
    Scanner s1=new Scanner(System.in);
    int temp,total;
    void get(){
        System.out.println("enter temperture in celsius:");
        temp=s1.nextInt();
      
    }
    void display(){
        total=(temp*9/5)+32;
        System.out.println(total);
    }
     public static void main(String ar[]){
        javaPracticeProblem3 p1=new javaPracticeProblem3();
        p1.get();
        p1.display();
    }
}
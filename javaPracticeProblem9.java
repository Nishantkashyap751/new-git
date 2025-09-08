      import java.util.Scanner;
public class javaPracticeProblem9 {
    Scanner s1=new Scanner(System.in);
    double num1,num2,num3;
    double interest;
    void get(){
        System.out.println("enter first number:");
        num1=s1.nextInt();
        System.out.println("enter second number:");
        num2=s1.nextInt();
        System.out.println("enter third number:");
        num3=s1.nextInt();
      
      
    }
    void display(){
        interest=(num1+num2+num3)/3;
        System.out.println(interest);
    }
     public static void main(String ar[]){
        javaPracticeProblem9 p1=new javaPracticeProblem9();
        p1.get();
        p1.display();
    }
}
import java.util.Scanner;
public class javaPracticeProblem2 {
    Scanner s1=new Scanner(System.in);
    int num1,num2,total;
    void get(){
        System.out.println("enter first number:");
        num1=s1.nextInt();
        System.out.println("enter the second number:");
        num2=s1.nextInt();
    }
    void display(){
        total=num1+num2;
        System.out.println(total);
    }
    public static void main(String ar[]){
        javaPracticeProblem2 p1=new javaPracticeProblem2();
        p1.get();
        p1.display();
    }
}

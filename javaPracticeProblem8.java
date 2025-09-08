import java.util.Scanner;
public class javaPracticeProblem8 {
    Scanner s1=new Scanner(System.in);
    double base,exponent;
    double total;
    void get(){
        System.out.println("enter base:");
        base=s1.nextInt();
        System.out.println("enter exponent:");
        exponent=s1.nextInt();
      
      
    }
    void display(){
        total=Math.pow(base,exponent);
        System.out.println(total);
    }
     public static void main(String ar[]){
        javaPracticeProblem8 p1=new javaPracticeProblem8();
        p1.get();
        p1.display();
    }
}
      import java.util.Scanner;
public class javaPracticeProblem6 {
    Scanner s1=new Scanner(System.in);
    double principle,rate,time;
    double interest;
    void get(){
        System.out.println("enter principle:");
        principle=s1.nextInt();
        System.out.println("enter rate:");
        rate=s1.nextInt();
        System.out.println("enter time:");
        time=s1.nextInt();
      
      
    }
    void display(){
        interest=(principle*rate*time)/100;
        System.out.println(interest);
    }
     public static void main(String ar[]){
        javaPracticeProblem6 p1=new javaPracticeProblem6();
        p1.get();
        p1.display();
    }
}
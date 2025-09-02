//6. Write a program to check whether a number is positive, negative, or zero.
import java.util.Scanner;
public class question6 {
    public static void main(String ar[]){
            Scanner s1=new Scanner (System.in);
        System.out.println("enter the value :");
        int n=s1.nextInt();
        if (n>0){
            System.out.println("the value is positive:"+ n);

        }
        else if(n<0){
            System.out.println("the value is negative:"+ n);
        }
        else{
            System.out.println("the vale is 0" +n);
        }
    }
}

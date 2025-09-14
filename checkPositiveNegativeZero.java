import java.util.Scanner;
public class checkPositiveNegativeZero {
    Scanner s1=new Scanner(System.in);
    int number;

    void get(){
        System.out.println("enter the number: ");
        number=s1.nextInt();
    }
    void checkpostNegZero(){
        if(number>0){
                    System.out.println("the number is positive"+number);
        }
        else if(number==0){
                    System.out.println("the number is zero"+number);
        }
        else {
                    System.out.println("the number is negative"+number);
        }
    }
    public static void main(String ar[]){
        checkPositiveNegativeZero c1=new checkPositiveNegativeZero();
        c1.get();
        c1.checkpostNegZero();
    }
}

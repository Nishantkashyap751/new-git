import java.util.*;
import java.lang.*;
public class comparetwostringvariable{
    Scanner s1=new Scanner(System.in);
    String n1[];
    int size;
    String comp;
    void get(){
        System.out.println("enter the size: ");
        size=s1.nextInt();
        n1=new String[size];
        for(int i=0;i<size;i++){
            System.out.println("enter the string: ");
            n1[i]=s1.next();
        }
    }
    void compare(){
        System.out.println("enter the string to compare:");
        comp=s1.next();
        for(int i=0;i<size;i++){
            if(n1[i].equals(comp)){
                System.out.println("match found at index:"+i);
            }
        }
    }
}
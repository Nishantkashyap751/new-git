import java.util.Scanner;
public class calhandshakeinstudents {
    int n,handshakes;
    Scanner s1=new Scanner(System.in);
    void get(){
        System.out.println("enter the no. of students:- ");
        n=s1.nextInt();
    }
    void cal_handshakes(){
        handshakes=(n*(n-1))/2;
        System.out.println("the maximum number of handshakes are :- "+ handshakes);

    }
    public static void main(String ar[]){
        calhandshakeinstudents c1= new calhandshakeinstudents() ;
        c1.get();
        c1.cal_handshakes();

    }
}

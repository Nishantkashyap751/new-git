import java.util.Scanner;
public class springseason {
    static int month;
    static int day;
    Scanner s1=new Scanner(System.in);
    void get(){
        System.out.println("enter the month:- ");
        month=s1.nextInt();
        System.out.println("enter the day:- ");
        day=s1.nextInt();
    }
    public static boolean cal_springseason(){
     if(month==3 && day>=20){
            return true;
        };
        if(month>3 && month<6){
            return true;
        }
        if (month == 6 && day <= 20) {
            return true;
        }
        return false;
        
     }   
    public static void main(String ar[]){
        springseason c1= new springseason() ;
        c1.get();
        springseason.cal_springseason();

    }
}

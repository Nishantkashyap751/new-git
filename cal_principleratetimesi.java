import java.util.Scanner;
public class cal_principleratetimesi {
    double principle,ROI,SI,Time;
    Scanner s1 = new Scanner (System.in);
    void get(){
        System.out.println("enter the principle :- ");
        principle=s1.nextDouble();
        System.out.println("Enter Rate Of Interest :- ");
        ROI=s1.nextDouble();
        System.out.println("Enter Time:- ");
        Time=s1.nextDouble();
    }
    void cal_simpleInterest(){
        SI=principle*ROI*Time/100;
        System.out.println("The Simple Interest is "+ SI +", for Principle" + principle+ ", Rate of Interest "+ ROI +", and Time"+Time);
    }
    public static void main(String ar[]){
        cal_principleratetimesi c1= new cal_principleratetimesi();
        c1.get();
        c1.cal_simpleInterest();
    }
}

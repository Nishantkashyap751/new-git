import java.util.Scanner;
public class calroundesOfAthelete {
    Scanner s1=new Scanner(System.in);
    int side1,base,side2,perimeter,distance=5;
    double round ;
    void get(){
        System.out.println("\nenter the side1: "+"\nenter the base: "+ "\nenter the side2: ");
        side1=s1.nextInt();
        base=s1.nextInt();
        side2=s1.nextInt();
    }
    void cal_perimeter_rounds(){
        perimeter=side1+base+side2;
        round=distance/perimeter;
        System.out.println("the number of rounds to cover 5KM is "+round);
    }
    public static void main(String ar[]){
        calroundesOfAthelete c1=new calroundesOfAthelete();
        c1.get();
        c1.cal_perimeter_rounds();
    }
}

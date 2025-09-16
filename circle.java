import java.util.Scanner;
public class circle {
    Scanner s1=new Scanner(System.in);
    double radius,area;
    void get(){
        System.out.print("Enter radius of circle: ");
        radius = s1.nextDouble();
    }
    void display(){
        area = 3.14 * radius * radius;
        System.out.println("Area of Circle: "+area);
    }
    public static void main(String[] args) {
        circle obj = new circle();
        obj.get();
        obj.display();
    }
}

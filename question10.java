//10. Write a program to find the area of a circle using a final variable for PI.

public class question10 {
    question10(){
        final double pi=3.14;
        double radius=32.4;
        double area_of_circle=pi*Math.pow(radius,2);
        System.out.println("area of circle with final variable:"+area_of_circle);
    }
    public static void main(String ar[]){
        question10 s1=new question10();
    }
}

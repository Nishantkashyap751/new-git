//8. Write a program to demonstrate implicit and explicit type casting.
public class question8 {
    public static void main(String ar[]){
        int conversion1=23;
        float into=conversion1;
        System.out.println("implicit type casting: " +into); // lose-less type casting,widening conversion
        System.out.println("====================================================");
        double conversion2=45.43;
        int into1= (int) conversion2;
        System.out.println("explicit type casting: " + into1);
    }
}

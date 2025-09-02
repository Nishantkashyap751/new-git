//3. Write a program to swap two numbers using a third variable.
public class question3 {
    public static void main(String ar[]){
        int a=5;
        int b=6;
        System.out.println("original number\n" +"value of a(before): "+ a +"\n"+"value of b(before):"+ b);

        int c=a;
        a=b;
        b=c;
        System.out.println("swapping the two number using third variable:\n" +"value of a(After): "+ a +"\n"+"value of b(After):"+ b);
    }
}

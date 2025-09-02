//4. Write a program to swap two numbers without using a third variable.
public class question4 {
    public static void main(String ar[]){
        int a=5;
        int b=7;
        System.out.println("original number\n" +"value of a(before): "+ a +"\n"+"value of b(before):"+ b);

        a=a+b;//5+7=12
        b=a-b;//12-7=5
        a=a-b;//12-5
        System.out.println("swapping the two number without using third variable:\n" +"value of a(After): "+ a +"\n"+"value of b(After):"+ b);
    }
}

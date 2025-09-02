//12. Write a program to demonstrate type promotion in arithmetic operations.
public class question12 {
    //the promotion id moving from char->int->long->float->double
    public static void main(String ar[]){
        byte v1=23;
        short v2=21;
        char v3=2;
        int sum=v1+v2+v3;
        System.out.println("byte ,short ,char to int promotion: "+sum);

        int v4=23;
        double v5=23.2;
        double sum1=v4+v5;
        System.out.println("sum of int and double will promoted to double:"+ sum1);

        float v6=23.f;
        double v7=23.4;
        double sum2=v6+v7;
        System.out.println("sum of float and double will be promoted to double: "+ sum2);
    }
}

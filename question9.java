//9. Write a program to demonstrate autoboxing and unboxing.
public class question9 {
    public static void main(String ar[]){
        int primitiveint=23;
        Integer integerobject = primitiveint;
        System.out.println("Autoboxing:" + integerobject);
        System.out.println("====================================================");
        Integer integerobject2=23;
        int primitiveint2= integerobject;
        System.out.println("Unboxing:" + primitiveint2);
    }
}

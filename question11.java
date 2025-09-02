//11. Write a program to show the difference between char and String data types.
public class question11 {
    public static void main(String ar[]){
        char variable='A';
        String variable1="hello World!";
        String variable2="B";
        System.out.println("printing char variable:"+variable);
        System.out.println("printing String variable" + variable1);
        System.out.println("String variable can have one character: "+variable2);

        System.out.println("size taken by char variable: "+Character.BYTES);
        System.out.println("length taken by String variable: "+variable1.length());
        String variable3="";
        System.out.println("we can made an empty String: "+variable3);
        System.out.println("length taken by String variable: "+variable3.length());

    }
}

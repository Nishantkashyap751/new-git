//14. Write a program to use wrapper class methods to convert numbers to binary and hexadecimal.
public class question14 {
    public static void main(String ar[]){
        int variable=31;
        String binarystring=Integer.toBinaryString(variable);
        System.out.println("wrapper class to binary: "+binarystring);
        String hexastring=Integer.toHexString(variable);
        System.out.println("wrapper class to hexadecimal: " +hexastring);
}
}

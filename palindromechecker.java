public class palindromechecker {
    String text;
    void check(String a){
        text = a;
        String reversed = new StringBuilder(text).reverse().toString();
        if(text.equals(reversed)){
            System.out.println(text + " is a palindrome.");
        } else {
            System.out.println(text + " is not a palindrome.");
        }
    }
    public static void main(String[] args) {
        palindromechecker pc = new palindromechecker();
        pc.check("racecar");
        pc.check("hello");
    }
}

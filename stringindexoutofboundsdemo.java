import java.util.InputMismatchException;
import java.util.Scanner;
public class stringindexoutofboundsdemo {
 public static void demonstrateException(String text) {
        System.out.println("--- Demonstrating StringIndexOutOfBoundsException ---");
        System.out.println("The string is: \"" + text + "\"");
        System.out.println("Its length is: " + text.length());
        int invalidIndex = text.length(); 
        System.out.println("Attempting to access character at index " + invalidIndex);
        char character = text.charAt(invalidIndex);
        System.out.println("The character is: " + character);
    }
    public static void handleException(String text) {
        System.out.println("\n--- Handling StringIndexOutOfBoundsException ---");
        System.out.println("The string is: \"" + text + "\"");
        System.out.println("Its length is: " + text.length());
        int invalidIndex = text.length();
        try {
            System.out.println("Attempting to access character at index " + invalidIndex + " inside a try block...");
            char character = text.charAt(invalidIndex);
            System.out.println("The character is: " + character); 
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("An error occurred: StringIndexOutOfBoundsException caught!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("You cannot access an index that is out of the string's bounds.");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        try {
            System.out.print("Enter a string: ");
            userInput = scanner.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid string.");
            return; 
        } finally {
            scanner.close();
        }
        handleException(userInput);
    }
}
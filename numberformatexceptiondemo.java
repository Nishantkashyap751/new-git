import java.util.InputMismatchException;
import java.util.Scanner;

public class numberformatexceptiondemo {

    public static void generateNumberFormatException(String text) {
        System.out.println("--- Demonstrating NumberFormatException ---");
        System.out.println("Attempting to convert the text \"" + text + "\" to a number.");
        
        int number = Integer.parseInt(text);
        System.out.println("The converted number is: " + number);
    }

    public static void handleNumberFormatException(String text) {
        System.out.println("\n--- Handling NumberFormatException ---");
        try {
            System.out.println("Attempting to convert the text \"" + text + "\" to a number inside a try block...");
            int number = Integer.parseInt(text);
            System.out.println("The converted number is: " + number);
        } catch (NumberFormatException e) {
            System.out.println("An error occurred: NumberFormatException caught!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("You cannot convert this text to a valid number.");
        } catch (RuntimeException e) {
            System.out.println("An unexpected runtime exception occurred.");
            System.out.println("Error message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a string that is NOT a number (e.g., 'hello'): ");
            userInput = scanner.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        handleNumberFormatException(userInput);
    }
}
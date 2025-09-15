import java.util.InputMismatchException;
import java.util.Scanner;
public class illegalargumentexceptiondemo {

    public static void generateIllegalArgumentException(String text, int start, int end) {
        System.out.println("--- Demonstrating IllegalArgumentException ---");
        System.out.println("Attempting to get a substring from \"" + text + "\" with start index " + start + " and end index " + end);

        String sub = text.substring(start, end);
        System.out.println("The resulting substring is: " + sub);
    }
    public static void handleIllegalArgumentException(String text, int start, int end) {
        System.out.println("\n--- Handling IllegalArgumentException ---");
        try {
            System.out.println("Attempting to get a substring with invalid indices inside a try block...");
            String sub = text.substring(start, end);
            System.out.println("The resulting substring is: " + sub); // This line will not be reached
        } catch (IllegalArgumentException e) {
            System.out.println("An error occurred: IllegalArgumentException caught!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("The start index cannot be greater than the end index.");
        } catch (RuntimeException e) {
            System.out.println("An unexpected runtime exception occurred.");
            System.out.println("Error message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInputString = "";
        int startIndex = 0;
        int endIndex = 0;

        try {
            System.out.print("Enter a string: ");
            userInputString = scanner.next();

            System.out.print("Enter a start index: ");
            startIndex = scanner.nextInt();

            System.out.print("Enter an end index (less than the start index to cause an error): ");
            endIndex = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string followed by two integers.");
            return;
        } finally {
            scanner.close();
        }
        handleIllegalArgumentException(userInputString, startIndex, endIndex);
    }
}
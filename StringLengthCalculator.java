import java.util.InputMismatchException;
import java.util.Scanner;

public class StringLengthCalculator {

    public static int calculateStringLength(String inputString) {
        if (inputString == null) {
            return 0;
        }

        int count = 0;
        try {
            while (true) {
                inputString.charAt(count); 
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            
        }
        return count;
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

        int customLength = calculateStringLength(userInput);
        System.out.println("\nLength using custom method: " + customLength);

        int builtInLength = userInput.length();
        System.out.println("Length using built-in length() method: " + builtInLength);

        System.out.println("Are the results the same? " + (customLength == builtInLength));
    }
}
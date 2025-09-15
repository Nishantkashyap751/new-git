import java.util.InputMismatchException;
import java.util.Scanner;

public class firstnonrepeatingchar {
    private static final int ASCII_SIZE = 256;

    public static char findFirstNonRepeatingCharacter(String text) {
        if (text == null || text.isEmpty()) {
            return '\u0000'; 
        }

        int[] charFrequencies = new int[ASCII_SIZE];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            charFrequencies[ch]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (charFrequencies[ch] == 1) {
                return ch; 
            }
        }

        return '\u0000'; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a string: ");
            userInput = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        char resultChar = findFirstNonRepeatingCharacter(userInput);

        if (resultChar == '\u0000') {
            System.out.println("No non-repeating character found in the string.");
        } else {
            System.out.println("The first non-repeating character is: " + resultChar);
        }
    }
}
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class uniquecharacterfinder {

    public static int getStringLength(String inputString) {
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

    public static char[] findUniqueCharacters(String text) {
        int length = getStringLength(text);
        if (length == 0) {
            return new char[0];
        }

        char[] uniqueChars = new char[length];
        int uniqueCount = 0;

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;

            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == currentChar) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                uniqueChars[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }
        
        return Arrays.copyOf(uniqueChars, uniqueCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a string: ");
            userInput = scanner.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        char[] uniqueCharacters = findUniqueCharacters(userInput);
        System.out.println("\nOriginal string: " + userInput);
        System.out.println("Unique characters: " + new String(uniqueCharacters));
    }
}
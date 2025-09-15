import java.util.InputMismatchException;
import java.util.Scanner;

public class characterfrequencyfromunique {

    private static final int ASCII_SIZE = 256;

    public static char[] findUniqueCharacters(String text) {
        if (text == null || text.isEmpty()) {
            return new char[0];
        }

        StringBuilder uniqueCharsBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < uniqueCharsBuilder.length(); j++) {
                if (uniqueCharsBuilder.charAt(j) == currentChar) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueCharsBuilder.append(currentChar);
            }
        }
        return uniqueCharsBuilder.toString().toCharArray();
    }

    public static String[][] getUniqueCharacterFrequencies(String text) {
        if (text == null || text.isEmpty()) {
            return new String[0][0];
        }

        int[] frequencies = new int[ASCII_SIZE];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequencies[ch]++;
        }

        char[] uniqueChars = findUniqueCharacters(text);

        String[][] result = new String[uniqueChars.length][2];
        for (int i = 0; i < uniqueChars.length; i++) {
            char uniqueChar = uniqueChars[i];
            result[i][0] = String.valueOf(uniqueChar);
            result[i][1] = String.valueOf(frequencies[uniqueChar]);
        }
        return result;
    }

    public static void displayFrequencies(String[][] data) {
        if (data.length == 0) {
            System.out.println("No characters to display.");
            return;
        }

        System.out.println("\n-----------------------");
        System.out.println("| Character | Frequency |");
        System.out.println("-----------------------");
        for (String[] row : data) {
            System.out.printf("| %-9s | %-9s |\n", row[0], row[1]);
        }
        System.out.println("-----------------------");
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

        String[][] uniqueCharFrequencies = getUniqueCharacterFrequencies(userInput);
        displayFrequencies(uniqueCharFrequencies);
    }
}
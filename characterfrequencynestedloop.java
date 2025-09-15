import java.util.InputMismatchException;
import java.util.Scanner;

public class characterfrequencynestedloop {

    public static String[][] getCharacterFrequencies(String text) {
        if (text == null || text.isEmpty()) {
            return new String[0][0];
        }

        char[] charArray = text.toCharArray();
        int[] frequencyArray = new int[charArray.length];
        int totalUniqueChars = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != '0') {
                frequencyArray[i] = 1;
                for (int j = i + 1; j < charArray.length; j++) {
                    if (charArray[i] == charArray[j]) {
                        frequencyArray[i]++;
                        charArray[j] = '0'; 
                    }
                }
                totalUniqueChars++;
            }
        }

        String[][] result = new String[totalUniqueChars][2];
        int resultIndex = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != '0') {
                result[resultIndex][0] = String.valueOf(charArray[i]);
                result[resultIndex][1] = String.valueOf(frequencyArray[i]);
                resultIndex++;
            }
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

        String[][] characterFrequencies = getCharacterFrequencies(userInput);
        displayFrequencies(characterFrequencies);
    }
}
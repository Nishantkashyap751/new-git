import java.util.InputMismatchException;
import java.util.Scanner;

public class characterfrequencycounter {

    private static final int ASCII_SIZE = 256;

    public static int[][] getCharacterFrequencies(String text) {
        if (text == null || text.isEmpty()) {
            return new int[0][0];
        }

        int[] frequencies = new int[ASCII_SIZE];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequencies[ch]++;
        }

        int uniqueCharCount = 0;
        for (int frequency : frequencies) {
            if (frequency > 0) {
                uniqueCharCount++;
            }
        }

        int[][] result = new int[uniqueCharCount][2];
        int resultIndex = 0;

        for (int i = 0; i < ASCII_SIZE; i++) {
            if (frequencies[i] > 0) {
                result[resultIndex][0] = i; 
                result[resultIndex][1] = frequencies[i]; 
                resultIndex++;
            }
        }
        return result;
    }

    public static void displayFrequencies(int[][] data) {
        if (data.length == 0) {
            System.out.println("The string is empty.");
            return;
        }

        System.out.println("\n-----------------------");
        System.out.println("| Character | Frequency |");
        System.out.println("-----------------------");
        for (int[] row : data) {
            char ch = (char) row[0];
            int frequency = row[1];
            System.out.printf("| %-9s | %-9d |\n", ch, frequency);
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

        int[][] characterFrequencies = getCharacterFrequencies(userInput);
        displayFrequencies(characterFrequencies);
    }
}
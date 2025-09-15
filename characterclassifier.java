import java.util.InputMismatchException;
import java.util.Scanner;

public class characterclassifier {

    public static String getCharacterType(char ch) {
        char lowerCaseCh = ch;
        if (ch >= 'A' && ch <= 'Z') {
            lowerCaseCh = (char) (ch + 32);
        }

        if (lowerCaseCh >= 'a' && lowerCaseCh <= 'z') {
            if (lowerCaseCh == 'a' || lowerCaseCh == 'e' || lowerCaseCh == 'i' || lowerCaseCh == 'o' || lowerCaseCh == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }
    public static String[][] analyzeStringCharacters(String text) {
        if (text == null) {
            return new String[0][0];
        }

        String[][] analysisResult = new String[text.length()][2];
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            analysisResult[i][0] = String.valueOf(currentChar);
            analysisResult[i][1] = getCharacterType(currentChar);
        }
        return analysisResult;
    }

    public static void displayDataInTable(String[][] data) {
        if (data.length == 0) {
            System.out.println("No data to display.");
            return;
        }

        System.out.println("\n-----------------------------");
        System.out.println("| Character | Type        |");
        System.out.println("-----------------------------");
        for (String[] row : data) {
            System.out.printf("| %-9s | %-11s |\n", row[0], row[1]);
        }
        System.out.println("-----------------------------");
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

        String[][] characterData = analyzeStringCharacters(userInput);
        displayDataInTable(characterData);
    }
}
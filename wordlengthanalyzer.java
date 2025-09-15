import java.util.InputMismatchException;
import java.util.Scanner;

public class wordlengthanalyzer {

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


    public static String[] splitStringManually(String text) {
        int length = getStringLength(text);
        if (length == 0) {
            return new String[0];
        }

        String processedText = text + " ";
        int wordCount = 0;
        for (int i = 0; i < getStringLength(processedText); i++) {
            if (processedText.charAt(i) == ' ') {
                wordCount++;
            }
        }

        String[] words = new String[wordCount];
        int startIndex = 0;
        int wordIndex = 0;

        for (int i = 0; i < getStringLength(processedText); i++) {
            if (processedText.charAt(i) == ' ') {
                words[wordIndex] = processedText.substring(startIndex, i);
                startIndex = i + 1;
                wordIndex++;
            }
        }
        return words;
    }

    public static String[][] getWordsWithLengths(String[] words) {
        String[][] wordData = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            wordData[i][0] = words[i];
            wordData[i][1] = String.valueOf(getStringLength(words[i]));
        }
        return wordData;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a sentence: ");
            userInput = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a sentence.");
            return;
        } finally {
            scanner.close();
        }

        String[] words = splitStringManually(userInput);
        String[][] wordDetails = getWordsWithLengths(words);

        System.out.println("\n-----------------");
        System.out.println("| Word\t| Length\t|");
        System.out.println("-----------------");

        for (int i = 0; i < wordDetails.length; i++) {
            String word = wordDetails[i][0];
            int length = Integer.parseInt(wordDetails[i][1]);
            System.out.printf("| %-6s| %-7d|\n", word, length);
        }
        System.out.println("-----------------");
    }
}
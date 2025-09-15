import java.util.InputMismatchException;
import java.util.Scanner;

public class wordlengthfinder {

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
        if (text == null || getStringLength(text) == 0) {
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
   
    public static int[] findShortestAndLongestLengths(String[][] wordData) {
        if (wordData == null || wordData.length == 0) {
            return new int[]{0, 0};
        }

        int shortestLength = Integer.parseInt(wordData[0][1]);
        int longestLength = Integer.parseInt(wordData[0][1]);

        for (int i = 1; i < wordData.length; i++) {
            int currentLength = Integer.parseInt(wordData[i][1]);
            if (currentLength < shortestLength) {
                shortestLength = currentLength;
            }
            if (currentLength > longestLength) {
                longestLength = currentLength;
            }
        }
        return new int[]{shortestLength, longestLength};
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
        if (words.length == 0) {
            System.out.println("No words found in the input string.");
            return;
        }

        String[][] wordDetails = getWordsWithLengths(words);
        int[] lengths = findShortestAndLongestLengths(wordDetails);

        int shortestLength = lengths[0];
        int longestLength = lengths[1];

        System.out.println("\nShortest word length: " + shortestLength);
        System.out.println("Longest word length: " + longestLength);
    }
}
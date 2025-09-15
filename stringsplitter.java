import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class stringsplitter {

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

        int wordCount = 1;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                wordCount++;
            }
        }

        String[] words = new String[wordCount];
        int wordIndex = 0;
        int startIndex = 0;

        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                words[wordIndex] = text.substring(startIndex, i);
                startIndex = i + 1;
                wordIndex++;
            }
        }
        words[wordIndex] = text.substring(startIndex, length);

        return words;
    }


    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a sentence: ");
            userInput = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        String[] customSplit = splitStringManually(userInput);
        System.out.println("\nWords from manual split: " + Arrays.toString(customSplit));

        String[] builtInSplit = userInput.split(" ");
        System.out.println("Words from built-in split(): " + Arrays.toString(builtInSplit));

        boolean areArraysEqual = compareStringArrays(customSplit, builtInSplit);
        System.out.println("Do the two arrays match? " + areArraysEqual);
    }
}
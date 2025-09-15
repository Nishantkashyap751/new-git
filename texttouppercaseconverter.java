import java.util.Scanner;

public class texttouppercaseconverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the complete text:");
            String userInputText = scanner.nextLine();

            String convertedText = convertToUppercaseUsingCharAt(userInputText);
            String builtInUppercaseText = userInputText.toUpperCase();

            boolean areEqual = compareStringsUsingCharAt(convertedText, builtInUppercaseText);

            System.out.println("Converted text using custom method: " + convertedText);
            System.out.println("Converted text using built-in toUpperCase(): " + builtInUppercaseText);
            System.out.println("Are both uppercase strings equal? " + areEqual);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static String convertToUppercaseUsingCharAt(String inputText) {
        if (inputText == null) {
            return null;
        }

        StringBuilder uppercaseBuilder = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                char uppercaseChar = (char) (currentChar - 32);
                uppercaseBuilder.append(uppercaseChar);
            } else {
                uppercaseBuilder.append(currentChar);
            }
        }

        return uppercaseBuilder.toString();
    }

    public static boolean compareStringsUsingCharAt(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            return false;
        }

        if (firstString.length() != secondString.length()) {
            return false;
        }

        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) != secondString.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

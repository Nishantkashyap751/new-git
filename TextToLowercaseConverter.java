import java.util.Scanner;

public class TextToLowercaseConverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the complete text:");
            String userInputText = scanner.nextLine();

            String convertedText = convertToLowercase(userInputText);
            String builtInLowercaseText = userInputText.toLowerCase();

            boolean areEqual = compareStrings(convertedText, builtInLowercaseText);

            System.out.println("Converted text using custom method: " + convertedText);
            System.out.println("Converted text using built-in toLowerCase(): " + builtInLowercaseText);
            System.out.println("Are both lowercase strings equal? " + areEqual);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static String convertToLowercase(String inputText) {
        if (inputText == null) {
            return null;
        }

        StringBuilder lowercaseBuilder = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                lowercaseBuilder.append((char) (currentChar + 32));
            } else {
                lowercaseBuilder.append(currentChar);
            }
        }

        return lowercaseBuilder.toString();
    }

    public static boolean compareStrings(String firstString, String secondString) {
        if (firstString == null && secondString == null) {
            return true;
        }
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

import java.util.InputMismatchException;
import java.util.Scanner;
public class SubstringComparator {

    public static String createSubstringManually(String originalString, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > originalString.length() || startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("Indices are out of bounds or invalid.");
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            resultBuilder.append(originalString.charAt(i));
        }
        return resultBuilder.toString();
    }
    public static boolean compareStrings(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mainString;
        int startIndex;
        int endIndex;

        try {
            System.out.print("Enter the main string: ");
            mainString = scanner.next();
            System.out.print("Enter the starting index (inclusive): ");
            startIndex = scanner.nextInt();
            System.out.print("Enter the ending index (exclusive): ");
            endIndex = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a string and two integers.");
            return;
        } finally {
            scanner.close();
        }
        try {
            String customSubstring = createSubstringManually(mainString, startIndex, endIndex);
            String builtInSubstring = mainString.substring(startIndex, endIndex);
            boolean areEqual = compareStrings(customSubstring, builtInSubstring);
            System.out.println("\nCustom Substring: " + customSubstring);
            System.out.println("Built-in Substring: " + builtInSubstring);
            System.out.println("Do the substrings match? " + areEqual);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("\nError: " + e.getMessage());
            System.out.println("Please ensure indices are within the string's length (" + mainString.length() + ").");
        }
    }
}
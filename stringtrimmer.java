import java.util.InputMismatchException;
import java.util.Scanner;

public class stringtrimmer {

    public static int[] getTrimmedIndices(String text) {
        if (text == null) {
            return new int[]{0, 0};
        }

        int startIndex = 0;
        int endIndex = text.length() - 1;

        while (startIndex < text.length() && text.charAt(startIndex) == ' ') {
            startIndex++;
        }

        if (startIndex > endIndex) {
            return new int[]{0, 0};
        }

        while (endIndex >= startIndex && text.charAt(endIndex) == ' ') {
            endIndex--;
        }

        return new int[]{startIndex, endIndex + 1}; 
    }

    public static String createSubstringManually(String originalString, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > originalString.length() || startIndex > endIndex) {
            throw new StringIndexOutOfBoundsException("Invalid start or end index.");
        }

        StringBuilder substringBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            substringBuilder.append(originalString.charAt(i));
        }
        return substringBuilder.toString();
    }

    public static boolean areStringsEqual(String str1, String str2) {
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
        String userInput = "";

        try {
            System.out.print("Enter a string with leading and trailing spaces: ");
            userInput = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        int[] indices = getTrimmedIndices(userInput);
        int startIndex = indices[0];
        int endIndex = indices[1];

        String customTrimmed = createSubstringManually(userInput, startIndex, endIndex);

        String builtInTrimmed = userInput.trim();

        System.out.println("\nOriginal String: '" + userInput + "'");
        System.out.println("Custom Trimmed String: '" + customTrimmed + "'");
        System.out.println("Built-in Trimmed String: '" + builtInTrimmed + "'");

        boolean areEqual = areStringsEqual(customTrimmed, builtInTrimmed);
        System.out.println("\nDo the two trimmed strings match? " + areEqual);
    }
}
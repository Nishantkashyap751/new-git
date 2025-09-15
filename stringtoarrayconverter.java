import java.util.InputMismatchException;
import java.util.Scanner;
public class stringtoarrayconverter {
    public static char[] convertStringToCharArray(String inputString) {
        if (inputString == null) {
            return new char[0];
        }
        char[] charArray = new char[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            charArray[i] = inputString.charAt(i);
        }
        return charArray;
    }
    public static boolean areCharArraysEqual(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a string: ");
            userInput = scanner.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }
        char[] customCharArray = convertStringToCharArray(userInput);
        System.out.println("\nResult from user-defined method: " + new String(customCharArray));
        char[] builtInCharArray = userInput.toCharArray();
        System.out.println("Result from built-in toCharArray() method: " + new String(builtInCharArray));
        boolean areResultsSame = areCharArraysEqual(customCharArray, builtInCharArray);
        System.out.println("\nDo the two results match? " + areResultsSame);
    }
}
import java.util.InputMismatchException;
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        int start = 0;
        int end = text.length() - 1;
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (text.charAt(start) != text.charAt(end)) {
            return false;
        }
        return isPalindromeRecursive(text, start + 1, end - 1);
    }


    public static String reverseString(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed.append(text.charAt(i));
        }
        return reversed.toString();
    }

    public static boolean isPalindromeUsingReverse(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        String reversedText = reverseString(text);
        return text.equals(reversedText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.print("Enter a string to check for palindrome: ");
            userInput = scanner.nextLine().trim().toLowerCase(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        System.out.println("\n--- Palindrome Check Results for '" + userInput + "' ---");

        boolean isPalindrome1 = isPalindromeIterative(userInput);
        System.out.println("Result (Iterative Method): " + isPalindrome1);

        boolean isPalindrome2 = isPalindromeRecursive(userInput, 0, userInput.length() - 1);
        System.out.println("Result (Recursive Method): " + isPalindrome2);
        
        boolean isPalindrome3 = isPalindromeUsingReverse(userInput);
        System.out.println("Result (Reverse String Method): " + isPalindrome3);
    }
}
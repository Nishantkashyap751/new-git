import java.util.InputMismatchException;
import java.util.Scanner;

public class anagramchecker {

    private static final int ASCII_SIZE = 256;

    public static boolean areAnagrams(String text1, String text2) {
        if (text1.length() != text2.length()) {
            return false;
        }

        int[] charFrequencies = new int[ASCII_SIZE];

        for (int i = 0; i < text1.length(); i++) {
            charFrequencies[text1.charAt(i)]++;
        }

        for (int i = 0; i < text2.length(); i++) {
            charFrequencies[text2.charAt(i)]--;
        }

        for (int frequency : charFrequencies) {
            if (frequency != 0) {
                return false; 
            }
        }

        return true; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text1 = "";
        String text2 = "";

        try {
            System.out.print("Enter the first string: ");
            text1 = scanner.nextLine().toLowerCase().replace(" ", ""); 
            System.out.print("Enter the second string: ");
            text2 = scanner.nextLine().toLowerCase().replace(" ", ""); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a string.");
            return;
        } finally {
            scanner.close();
        }

        boolean isAnagram = areAnagrams(text1, text2);
        
        System.out.println("\nText 1: '" + text1 + "'");
        System.out.println("Text 2: '" + text2 + "'");

        if (isAnagram) {
            System.out.println("The two strings are anagrams.");
        } else {
            System.out.println("The two strings are not anagrams.");
        }
    }
}
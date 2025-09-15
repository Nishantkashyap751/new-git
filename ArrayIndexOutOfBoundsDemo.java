import java.util.Scanner;

public class ArrayIndexOutOfBoundsDemo {

    public static String[] getNamesFromUser (int numberOfNames) {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[numberOfNames];
        System.out.println("Enter " + numberOfNames + " names:");
        for (int i = 0; i < numberOfNames; i++) {
            names[i] = scanner.nextLine();
        }
        return names;
    }

    public static void generateArrayIndexOutOfBoundsException(String[] names) {
        System.out.println("Generating ArrayIndexOutOfBoundsException...");
        System.out.println("Accessing element at index " + names.length + ": " + names[names.length]);
    }

    public static void handleArrayIndexOutOfBoundsException(String[] names) {
        System.out.println("Handling ArrayIndexOutOfBoundsException with try-catch...");
        try {
            System.out.println("Accessing element at index " + names.length + ": " + names[names.length]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Caught generic RuntimeException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final int NUMBER_OF_NAMES = 3; 

        String[] userNames = getNamesFromUser (NUMBER_OF_NAMES);
        handleArrayIndexOutOfBoundsException(userNames);
    }
}

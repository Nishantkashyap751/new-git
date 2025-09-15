public class NullPointerExceptionDemo {
    public static void generateNullPointerException() {
        System.out.println("--- Demonstrating NullPointerException ---");
        String text = null; 
        System.out.println("The value of the string is: " + text);
        System.out.println("Attempting to get the length of the string...");
        int length = text.length(); 
        System.out.println("The length of the string is: " + length);
    }
    public static void handleNullPointerException() {
        System.out.println("\n--- Handling NullPointerException ---");
        String safeText = null; 

        try {
            System.out.println("Attempting to get the length of the string inside a try block...");
            int length = safeText.length();
            System.out.println("The length of the string is: " + length);
        } catch (NullPointerException e) {
            System.out.println("An error occurred: NullPointerException caught!");
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Cannot perform an action on a null reference.");
        }
    }
    public static void main(String[] args) {
        handleNullPointerException();
    }
}
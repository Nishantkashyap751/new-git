public class question16{


    public static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {

        System.out.println("Maximum login attempts allowed: " + MAX_ATTEMPTS);

        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            System.out.println("Attempt number: " + i);
        }

     
        System.out.println("\nThe value of MAX_ATTEMPTS cannot be changed.");
    }
}

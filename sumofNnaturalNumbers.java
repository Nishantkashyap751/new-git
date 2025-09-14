import java.util.Scanner;

public class sumofNnaturalNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer n: ");
        int n = scanner.nextInt();

        int sum = calculateSum(n);

        System.out.println("Sum of first " + n + " natural numbers is: " + sum);

        scanner.close();
    }

    public static int calculateSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}

import java.util.Scanner;

public class SumNaturalNumbers {

    public static int recursiveSum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + recursiveSum(n - 1);
    }

    public static int formulaSum(int n) {
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a natural number: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter an integer.");
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Input is not a natural number. Exiting.");
            scanner.close();
            return;
        }

        int sumRecursive = recursiveSum(n);

        int sumFormula = formulaSum(n);

        System.out.println("Sum of first " + n + " natural numbers (recursive): " + sumRecursive);
        System.out.println("Sum of first " + n + " natural numbers (formula): " + sumFormula);

        if (sumRecursive == sumFormula) {
            System.out.println("Both methods give the same result. Computation is correct.");
        } else {
            System.out.println("Results differ! There might be an error.");
        }

        scanner.close();
    }
}

import java.util.Scanner;

public class NaturalNumberSumForCompare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        if (n >= 1) {
            int sumFormula = n * (n + 1) / 2;
            int sumFor = 0;
            for (int i = 1; i <= n; i++) {
                sumFor += i;
            }
            System.out.println("Sum using formula: " + sumFormula);
            System.out.println("Sum using for loop: " + sumFor);
            System.out.println("Both are equal? " + (sumFormula == sumFor));
        } else {
            System.out.println("The number " + n + " is not a natural number");
        }
        sc.close();
    }
}
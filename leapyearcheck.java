import java.util.Scanner;

public class leapyearcheck {

    public static boolean isLeapYear(int year) {
        if (year < 1582) {
            System.out.println("Year must be 1582 or later.");
            return false;
        }
       
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a year (>= 1582): ");
        int year = scanner.nextInt();

        if (isLeapYear(year)) {
            System.out.println(year + " is a Leap Year.");
        } else {
            System.out.println(year + " is NOT a Leap Year.");
        }

        System.out.println("\nChecking some example years:");
        int[] exampleYears = {1600, 1700, 2000, 2023, 2024};
        for (int y : exampleYears) {
            System.out.println(y + ": " + (isLeapYear(y) ? "Leap Year" : "Not a Leap Year"));
        }

        scanner.close();
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalendarDisplay {

    private static final String[] MONTH_NAMES = {
        "", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    private static final int[] DAYS_IN_MONTH = {
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getNumberOfDays(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month.");
        }
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return DAYS_IN_MONTH[month];
    }

    public static int getFirstDayOfMonth(int month, int year) {
        int y0 = year - (14 - month) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = month + 12 * ((14 - month) / 12) - 2;
        int d0 = (1 + x + 31 * m0 / 12) % 7;
        return d0;
    }
    public static void displayCalendar(int month, int year) {
        System.out.println("\n  " + MONTH_NAMES[month] + " " + year);
        System.out.println("---------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");

        int firstDay = getFirstDayOfMonth(month, year);
        int numberOfDays = getNumberOfDays(month, year);

        for (int i = 0; i < firstDay; i++) {
            System.out.printf("    ");
        }

        for (int day = 1; day <= numberOfDays; day++) {
            System.out.printf("%3d ", day);
            if ((day + firstDay) % 7 == 0 || day == numberOfDays) {
                System.out.println();
            }
        }
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = 0;
        int year = 0;

        try {
            System.out.print("Enter the month (1-12): ");
            month = scanner.nextInt();
            System.out.print("Enter the year: ");
            year = scanner.nextInt();

            if (month < 1 || month > 12 || year < 0) {
                System.out.println("Invalid month or year entered. Please try again.");
            } else {
                displayCalendar(month, year);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for month and year.");
        } finally {
            scanner.close();
        }
    }
}
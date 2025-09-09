import java.util.Scanner;

public class EmployeeBonusCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int EMPLOYEES = 10;
        double[] salary = new double[EMPLOYEES];
        double[] years = new double[EMPLOYEES];
        double[] newSalary = new double[EMPLOYEES];
        double[] bonus = new double[EMPLOYEES];
        double totalOld = 0, totalNew = 0, totalBonus = 0;

        for (int i = 0; i < EMPLOYEES; i++) {
            System.out.print("Enter salary of employee " + (i+1) + ": ");
            double s = sc.nextDouble();
            System.out.print("Enter years of service of employee " + (i+1) + ": ");
            double y = sc.nextDouble();
            if (s <= 0 || y < 0) {
                System.out.println("Invalid input, try again.");
                i--;
                continue;
            }
            salary[i] = s;
            years[i] = y;
        }

        for (int i = 0; i < EMPLOYEES; i++) {
            if (years[i] > 5) {
                bonus[i] = salary[i] * 0.05;
            } else {
                bonus[i] = salary[i] * 0.02;
            }
            newSalary[i] = salary[i] + bonus[i];
            totalOld += salary[i];
            totalNew += newSalary[i];
            totalBonus += bonus[i];
        }

        System.out.println("Total Old Salary: " + totalOld);
        System.out.println("Total Bonus: " + totalBonus);
        System.out.println("Total New Salary: " + totalNew);
    }
}
import java.util.Random;

public class zarabonuscalculator {

    public static int[][] generateEmployeeData(int numEmployees) {
        int[][] data = new int[numEmployees][2];
        Random rand = new Random();

        for (int i = 0; i < numEmployees; i++) {
            int salary = 10000 + rand.nextInt(90000);
            int years = 1 + rand.nextInt(10);

            data[i][0] = salary;
            data[i][1] = years;
        }
        return data;
    }

    public static double[][] calculateNewSalaryAndBonus(int[][] oldData) {
        int numEmployees = oldData.length;
        double[][] newData = new double[numEmployees][2];

        for (int i = 0; i < numEmployees; i++) {
            int salary = oldData[i][0];
            int years = oldData[i][1];
            double bonusPercent = (years > 5) ? 0.05 : 0.02;
            double bonus = salary * bonusPercent;
            double newSalary = salary + bonus;

            newData[i][0] = newSalary;
            newData[i][1] = bonus;
        }
        return newData;
    }

    public static void displaySummary(int[][] oldData, double[][] newData) {
        double sumOldSalary = 0;
        double sumNewSalary = 0;
        double sumBonus = 0;

        System.out.printf("%-10s %-12s %-15s %-15s %-15s %-10s\n",
                "Emp ID", "Old Salary", "Years of Service", "Bonus Amount", "New Salary", "Bonus %");

        for (int i = 0; i < oldData.length; i++) {
            int oldSalary = oldData[i][0];
            int years = oldData[i][1];
            double newSalary = newData[i][0];
            double bonus = newData[i][1];
            double bonusPercent = (years > 5) ? 5 : 2;

            sumOldSalary += oldSalary;
            sumNewSalary += newSalary;
            sumBonus += bonus;

            System.out.printf("%-10d %-12d %-15d %-15.2f %-15.2f %-10.0f%%\n",
                    (i + 1), oldSalary, years, bonus, newSalary, bonusPercent);
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-12.2f %-15s %-15.2f %-15.2f %-10s\n",
                "TOTAL", sumOldSalary, "", sumBonus, sumNewSalary, "");
    }

    public static void main(String[] args) {
        int numEmployees = 10;

        int[][] employeeData = generateEmployeeData(numEmployees);

        double[][] newSalaryBonusData = calculateNewSalaryAndBonus(employeeData);

        displaySummary(employeeData, newSalaryBonusData);
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class bmicalculator {

    private static final int NUMBER_OF_PERSONS = 10;
    private static final int HEIGHT_INDEX = 1;
    private static final int WEIGHT_INDEX = 0;
    private static final int CM_TO_M_DIVISOR = 100;

    public static String[] calculateBmiAndStatus(double weightKg, double heightCm) {
        double heightM = heightCm / CM_TO_M_DIVISOR;

        double bmi = weightKg / (heightM * heightM);

        bmi = Math.round(bmi * 100.0) / 100.0;

        String status;
        if (bmi <= 18.4) {
            status = "Underweight";
        } else if (bmi <= 24.9) {
            status = "Normal";
        } else if (bmi <= 39.9) {
            status = "Overweight";
        } else {
            status = "Obese";
        }

        return new String[]{String.valueOf(bmi), status};
    }

    public static String[][] processBmiData(double[][] personData) {
        String[][] processedData = new String[personData.length][4];

        for (int i = 0; i < personData.length; i++) {
            double weight = personData[i][WEIGHT_INDEX];
            double height = personData[i][HEIGHT_INDEX];

            String[] bmiAndStatus = calculateBmiAndStatus(weight, height);

            processedData[i][0] = String.valueOf(weight);
            processedData[i][1] = String.valueOf(height);
            processedData[i][2] = bmiAndStatus[0]; // BMI
            processedData[i][3] = bmiAndStatus[1]; // Status
        }
        return processedData;
    }

    public static void displayBmiTable(String[][] data) {
        System.out.println("\n--- BMI Status for the Team ---");
        System.out.println("-------------------------------------------------");
        System.out.println("| Person | Weight (kg) | Height (cm) | BMI | Status      |");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < data.length; i++) {
            System.out.printf("| %-6d | %-11s | %-11s | %-3s | %-11s |\n",
                    (i + 1), data[i][0], data[i][1], data[i][2], data[i][3]);
        }

        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] personData = new double[NUMBER_OF_PERSONS][2];

        try {
            System.out.println("Enter the weight (kg) and height (cm) for " + NUMBER_OF_PERSONS + " persons:");
            for (int i = 0; i < NUMBER_OF_PERSONS; i++) {
                System.out.println("\n--- Person " + (i + 1) + " ---");
                System.out.print("Enter weight in kg: ");
                personData[i][WEIGHT_INDEX] = scanner.nextDouble();

                System.out.print("Enter height in cm: ");
                personData[i][HEIGHT_INDEX] = scanner.nextDouble();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for weight and height.");
            return;
        } finally {
            scanner.close();
        }

        String[][] bmiResults = processBmiData(personData);
        displayBmiTable(bmiResults);
    }
}
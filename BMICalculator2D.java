import java.util.Scanner;

public class BMICalculator2D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int n = sc.nextInt();
        double[][] personData = new double[n][3];
        String[] status = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter height (m) of person " + (i+1) + ": ");
            personData[i][0] = sc.nextDouble();
            System.out.print("Enter weight (kg) of person " + (i+1) + ": ");
            personData[i][1] = sc.nextDouble();

            personData[i][2] = personData[i][1] / (personData[i][0] * personData[i][0]);
            if (personData[i][2] <= 18.4) status[i] = "Underweight";
            else if (personData[i][2] <= 24.9) status[i] = "Normal";
            else if (personData[i][2] <= 39.9) status[i] = "Overweight";
            else status[i] = "Obese";
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i+1) + ": H=" + personData[i][0] + " W=" + personData[i][1] +
                               " BMI=" + personData[i][2] + " Status=" + status[i]);
        }
    }
}
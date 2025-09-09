import java.util.Scanner;

public class StoreValuesArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] values = new double[10];
        double total = 0.0;
        int index = 0;

        while (true) {
            System.out.print("Enter a number (0 or negative to stop): ");
            double num = sc.nextDouble();
            if (num <= 0 || index == values.length) {
                break;
            }
            values[index] = num;
            index++;
        }

        for (int i = 0; i < index; i++) {
            total += values[i];
            System.out.println("Value " + (i + 1) + ": " + values[i]);
        }

        System.out.println("Total sum = " + total);
        sc.close();
    }
}

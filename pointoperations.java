import java.util.Scanner;

public class pointoperations {

    public static double euclideanDistance(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }

    public static double[] lineEquation(double x1, double y1, double x2, double y2) {
        double m, b;
        if (x2 - x1 == 0) {
            m = Double.POSITIVE_INFINITY;
            b = Double.NaN;
        } else {
            m = (y2 - y1) / (x2 - x1);
            b = y1 - m * x1;
        }
        return new double[]{m, b};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter x1: ");
        double x1 = scanner.nextDouble();

        System.out.print("Enter y1: ");
        double y1 = scanner.nextDouble();

        System.out.print("Enter x2: ");
        double x2 = scanner.nextDouble();

        System.out.print("Enter y2: ");
        double y2 = scanner.nextDouble();

        double distance = euclideanDistance(x1, y1, x2, y2);
        System.out.printf("Euclidean distance between (%.2f, %.2f) and (%.2f, %.2f) is: %.4f\n", x1, y1, x2, y2, distance);

        double[] lineParams = lineEquation(x1, y1, x2, y2);
        double m = lineParams[0];
        double b = lineParams[1];

        if (Double.isInfinite(m)) {
            System.out.printf("The line is vertical: x = %.2f\n", x1);
        } else {
            System.out.printf("Equation of the line: y = %.4fx + %.4f\n", m, b);
        }

        scanner.close();
    }
}

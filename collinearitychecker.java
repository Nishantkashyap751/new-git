import java.util.Scanner;

public class collinearitychecker {

    public static boolean areCollinearBySlope(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (x1 == x2 && x2 == x3) {
            return true;
        }

        double slopeAB = (x2 - x1 != 0) ? (y2 - y1) / (x2 - x1) : Double.POSITIVE_INFINITY;
        double slopeBC = (x3 - x2 != 0) ? (y3 - y2) / (x3 - x2) : Double.POSITIVE_INFINITY;

        return Math.abs(slopeAB - slopeBC) < 1e-9;
    }

    public static boolean areCollinearByArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double area = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));

        return area < 1e-9;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coordinates for point A:");
        System.out.print("x1: ");
        double x1 = scanner.nextDouble();
        System.out.print("y1: ");
        double y1 = scanner.nextDouble();

        System.out.println("Enter coordinates for point B:");
        System.out.print("x2: ");
        double x2 = scanner.nextDouble();
        System.out.print("y2: ");
        double y2 = scanner.nextDouble();

        System.out.println("Enter coordinates for point C:");
        System.out.print("x3: ");
        double x3 = scanner.nextDouble();
        System.out.print("y3: ");
        double y3 = scanner.nextDouble();

        boolean isCollinearBySlope = areCollinearBySlope(x1, y1, x2, y2, x3, y3);
        System.out.println("\n--- Results using Slope Formula ---");
        System.out.println("Are points collinear? " + isCollinearBySlope);

        boolean isCollinearByArea = areCollinearByArea(x1, y1, x2, y2, x3, y3);
        System.out.println("\n--- Results using Area of Triangle Formula ---");
        System.out.println("Are points collinear? " + isCollinearByArea);
        
        System.out.println("\n--- Verification with A(2,4), B(4,6), C(6,8) ---");
        boolean sampleSlope = areCollinearBySlope(2, 4, 4, 6, 6, 8);
        System.out.println("Are A(2,4), B(4,6), C(6,8) collinear by slope? " + sampleSlope);
        boolean sampleArea = areCollinearByArea(2, 4, 4, 6, 6, 8);
        System.out.println("Are A(2,4), B(4,6), C(6,8) collinear by area? " + sampleArea);

        scanner.close();
    }
}

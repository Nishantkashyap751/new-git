import java.util.Scanner;

public class TrigonometryCalculator {
    public double[] calculateTrigonometricFunctions(double angle) {
        double radians = Math.toRadians(angle);
        double sine = Math.sin(radians);
        double cosine = Math.cos(radians);
        double tangent = Math.tan(radians);

        return new double[] {sine, cosine, tangent};
    }
    public static void main(String[] args) {
        TrigonometryCalculator calculator = new TrigonometryCalculator();
        Scanner s1=new Scanner (System.in);

        double angle;
        System.out.println("enter the angle:");
        angle=s1.nextDouble();
        double[] results = calculator.calculateTrigonometricFunctions(angle);

        System.out.printf("Angle: %.2f degrees%n", angle);
        System.out.printf("Sine: %.4f%n", results[0]);
        System.out.printf("Cosine: %.4f%n", results[1]);
        System.out.printf("Tangent: %.4f%n", results[2]);
    }
}

import java.util.Arrays;

public class Numberchecker5 {

    public static int[] findFactors(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                count++;
            }
        }
        int[] factors = new int[count];
        int index = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                factors[index++] = i;
            }
        }
        return factors;
    }

    public static int greatestFactor(int num) {
        int[] factors = findFactors(num);
        return factors[factors.length - 1];
    }

    public static int sumOfFactors(int num) {
        int[] factors = findFactors(num);
        int sum = 0;
        for (int factor : factors) {
            sum += factor;
        }
        return sum;
    }

    public static long productOfFactors(int num) {
        int[] factors = findFactors(num);
        long product = 1;
        for (int factor : factors) {
            product *= factor;
        }
        return product;
    }

    public static long productOfCubeOfFactors(int num) {
        int[] factors = findFactors(num);
        long product = 1;
        for (int factor : factors) {
            product *= Math.pow(factor, 3);
        }
        return product;
    }

    public static boolean isPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    public static boolean isAbundantNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum > num;
    }

    public static boolean isDeficientNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum < num;
    }

    private static int factorial(int digit) {
        if (digit == 0 || digit == 1) {
            return 1;
        }
        int fact = 1;
        for (int i = 2; i <= digit; i++) {
            fact *= i;
        }
        return fact;
    }

    public static boolean isStrongNumber(int num) {
        int sum = 0;
        int temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            sum += factorial(digit);
            temp /= 10;
        }
        return sum == num;
    }

    public static void main(String[] args) {
        int number = 28;

        System.out.println("Number: " + number);

        int[] factors = findFactors(number);
        System.out.println("Factors: " + Arrays.toString(factors));

        System.out.println("Greatest Factor: " + greatestFactor(number));
        System.out.println("Sum of Factors: " + sumOfFactors(number));
        System.out.println("Product of Factors: " + productOfFactors(number));
        System.out.println("Product of Cube of Factors: " + productOfCubeOfFactors(number));

        System.out.println("Is Perfect Number? " + isPerfectNumber(number));
        System.out.println("Is Abundant Number? " + isAbundantNumber(number));
        System.out.println("Is Deficient Number? " + isDeficientNumber(number));

        int strongTestNumber = 145;
        System.out.println("Number: " + strongTestNumber);
        System.out.println("Is Strong Number? " + isStrongNumber(strongTestNumber));
    }
}

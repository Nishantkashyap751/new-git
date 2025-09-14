public class Numberchecker4 {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isNeon(int n) {
        int square = n * n;
        int sumDigits = 0;
        while (square > 0) {
            sumDigits += square % 10;
            square /= 10;
        }
        return sumDigits == n;
    }

    public static boolean isSpy(int n) {
        int sum = 0;
        int product = 1;
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            product *= digit;
            temp /= 10;
        }
        return sum == product;
    }

    public static boolean isAutomorphic(int n) {
        int square = n * n;
        String nStr = Integer.toString(n);
        String squareStr = Integer.toString(square);
        return squareStr.endsWith(nStr);
    }

    public static boolean isBuzz(int n) {
        return (n % 7 == 0) || (n % 10 == 7);
    }

    public static void main(String[] args) {
        int[] testNumbers = {5, 7, 9, 13, 19, 25, 49, 121, 153, 370};

        for (int num : testNumbers) {
            System.out.println("Number: " + num);
            System.out.println("Is Prime? " + isPrime(num));
            System.out.println("Is Neon? " + isNeon(num));
            System.out.println("Is Spy? " + isSpy(num));
            System.out.println("Is Automorphic? " + isAutomorphic(num));
            System.out.println("Is Buzz? " + isBuzz(num));
            System.out.println("-------------------------");
        }
    }
}

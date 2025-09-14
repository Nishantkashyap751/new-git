import java.util.Random;

public class FootballTeamHeights {

    public static void main(String[] args) {
        int[] heights = new int[11];
        Random rand = new Random();

        for (int i = 0; i < heights.length; i++) {
            heights[i] = rand.nextInt(101) + 150; // 0-100 + 150 = 150-250
        }

        System.out.print("Player heights (cm): ");
        for (int h : heights) {
            System.out.print(h + " ");
        }
        System.out.println();

        int sum = sumHeights(heights);
        double mean = meanHeight(heights);
        int shortest = shortestHeight(heights);
        int tallest = tallestHeight(heights);

        System.out.println("Sum of heights: " + sum + " cm");
        System.out.printf("Mean height: %.2f cm\n", mean);
        System.out.println("Shortest height: " + shortest + " cm");
        System.out.println("Tallest height: " + tallest + " cm");
    }

    public static int sumHeights(int[] arr) {
        int sum = 0;
        for (int height : arr) {
            sum += height;
        }
        return sum;
    }

    public static double meanHeight(int[] arr) {
        int sum = sumHeights(arr);
        return (double) sum / arr.length;
    }

    public static int shortestHeight(int[] arr) {
        int min = arr[0];
        for (int height : arr) {
            if (height < min) {
                min = height;
            }
        }
        return min;
    }

    public static int tallestHeight(int[] arr) {
        int max = arr[0];
        for (int height : arr) {
            if (height > max) {
                max = height;
            }
        }
        return max;
    }
}

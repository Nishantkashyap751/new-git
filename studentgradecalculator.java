import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class studentgradecalculator {

    private static final int NUMBER_OF_SUBJECTS = 3;
    private static final int MAX_MARKS = 100;
    private static final int MIN_RANDOM_MARKS = 0;
    private static final int MAX_RANDOM_MARKS = 100;

    public static int[][] generateRandomScores(int numStudents) {
        Random random = new Random();
        int[][] scores = new int[numStudents][NUMBER_OF_SUBJECTS];
        for (int i = 0; i < numStudents; i++) {
            for (int j = 0; j < NUMBER_OF_SUBJECTS; j++) {
                scores[i][j] = random.nextInt(MAX_RANDOM_MARKS - MIN_RANDOM_MARKS + 1) + MIN_RANDOM_MARKS;
            }
        }
        return scores;
    }
    public static double[][] calculateMetrics(int[][] scores) {
        double[][] metrics = new double[scores.length][3]; 

        for (int i = 0; i < scores.length; i++) {
            int total = 0;
            for (int j = 0; j < NUMBER_OF_SUBJECTS; j++) {
                total += scores[i][j];
            }
            metrics[i][0] = total;
            metrics[i][1] = (double) total / NUMBER_OF_SUBJECTS;
            metrics[i][2] = (double) total / (NUMBER_OF_SUBJECTS * MAX_MARKS) * 100;

            metrics[i][2] = Math.round(metrics[i][2] * 100.0) / 100.0;
        }
        return metrics;
    }

    public static String getGrade(double percentage) {
        if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else if (percentage >= 40) {
            return "E";
        } else {
            return "R";
        }
    }

    public static String[][] assignGrades(double[][] percentages) {
        String[][] grades = new String[percentages.length][2];
        for (int i = 0; i < percentages.length; i++) {
            grades[i][0] = String.valueOf(i + 1);
            grades[i][1] = getGrade(percentages[i][2]);
        }
        return grades;
    }

    public static void displayScorecard(int[][] scores, double[][] metrics, String[][] grades) {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("| Student | Phy | Chem | Math | Total | Avg | %age  | Grade |");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < scores.length; i++) {
            String studentId = String.valueOf(i + 1);
            int phyScore = scores[i][0];
            int chemScore = scores[i][1];
            int mathScore = scores[i][2];
            int total = (int) metrics[i][0];
            double average = metrics[i][1];
            double percentage = metrics[i][2];
            String grade = grades[i][1];

            System.out.printf("| %-7s | %-3d | %-5d | %-5d| %-5d | %-3.1f | %-5.2f | %-5s |\n",
                    studentId, phyScore, chemScore, mathScore, total, average, percentage, grade);
        }

        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numStudents = 0;

        try {
            System.out.print("Enter the number of students: ");
            numStudents = scanner.nextInt();
            if (numStudents <= 0) {
                System.out.println("Number of students must be a positive integer.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        } finally {
            scanner.close();
        }

        int[][] studentScores = generateRandomScores(numStudents);

        double[][] studentMetrics = calculateMetrics(studentScores);

        String[][] studentGrades = assignGrades(studentMetrics);

        displayScorecard(studentScores, studentMetrics, studentGrades);
    }
}
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class votingeligibilitychecker {

    private static final int NUMBER_OF_STUDENTS = 10;
    private static final int VOTING_AGE = 18;
    private static final int MIN_RANDOM_AGE = 10;
    private static final int MAX_RANDOM_AGE = 25;

   
    public static int[] generateRandomAges(int numStudents) {
        Random random = new Random();
        int[] ages = new int[numStudents];
        for (int i = 0; i < numStudents; i++) {
            ages[i] = random.nextInt(MAX_RANDOM_AGE - MIN_RANDOM_AGE + 1) + MIN_RANDOM_AGE;
        }
        return ages;
    }

    public static String[][] checkVotingEligibility(int[] studentAges) {
        String[][] eligibilityData = new String[studentAges.length][2];
        for (int i = 0; i < studentAges.length; i++) {
            int age = studentAges[i];
            eligibilityData[i][0] = String.valueOf(age);

            if (age < 0) {
                eligibilityData[i][1] = "false";
            } else {
                eligibilityData[i][1] = String.valueOf(age >= VOTING_AGE);
            }
        }
        return eligibilityData;
    }

    public static void displayResultsInTable(String[][] data) {
        if (data.length == 0) {
            System.out.println("No data to display.");
            return;
        }

        System.out.println("\n-------------------------");
        System.out.println("| Age  | Can Vote?      |");
        System.out.println("-------------------------");
        for (String[] row : data) {
            String age = row[0];
            String canVote = row[1];
            System.out.printf("| %-4s | %-14s |\n", age, canVote);
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        try {
            System.out.println("This program will generate ages for " + NUMBER_OF_STUDENTS + " students.");
        } catch (InputMismatchException e) {
            System.out.println("An unexpected error occurred with input. Please try again.");
            return;
        } finally {
            scanner.close();
        }

        int[] studentAges = generateRandomAges(NUMBER_OF_STUDENTS);

        String[][] votingData = checkVotingEligibility(studentAges);

        displayResultsInTable(votingData);
    }
}
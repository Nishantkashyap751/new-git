import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";

    public static String getComputerChoice() {
        Random random = new Random();
        int randomNumber = random.nextInt(3); 

        switch (randomNumber) {
            case 0:
                return ROCK;
            case 1:
                return PAPER;
            case 2:
                return SCISSORS;
            default:
                return "invalid"; 
        }
    }
    public static String findWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "tie";
        }

        if ((userChoice.equals(ROCK) && computerChoice.equals(SCISSORS)) ||
            (userChoice.equals(SCISSORS) && computerChoice.equals(PAPER)) ||
            (userChoice.equals(PAPER) && computerChoice.equals(ROCK))) {
            return "user";
        } else {
            return "computer";
        }
    }
    public static String[][] calculateWinStats(int userWins, int computerWins, int totalGames) {
        double userWinPercentage = (double) userWins / totalGames * 100;
        double computerWinPercentage = (double) computerWins / totalGames * 100;

        String[][] stats = new String[2][2];
        stats[0][0] = String.valueOf(userWins);
        stats[0][1] = String.format("%.2f%%", userWinPercentage);
        stats[1][0] = String.valueOf(computerWins);
        stats[1][1] = String.format("%.2f%%", computerWinPercentage);

        return stats;
    }
    public static void displayGameResults(String[][] gameResults, String[][] finalStats) {
        System.out.println("\n--- Game Results ---");
        System.out.println("-------------------------------------------------");
        System.out.println("| Game | User Choice | Computer Choice | Winner  |");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < gameResults.length; i++) {
            System.out.printf("| %-4d | %-11s | %-15s | %-7s |\n", (i + 1), gameResults[i][0], gameResults[i][1], gameResults[i][2]);
        }
        System.out.println("-------------------------------------------------");

        System.out.println("\n--- Final Statistics ---");
        System.out.println("------------------------------------");
        System.out.println("| Player   | Wins | Win Percentage |");
        System.out.println("------------------------------------");
        System.out.printf("| User     | %-4s | %-14s |\n", finalStats[0][0], finalStats[0][1]);
        System.out.printf("| Computer | %-4s | %-14s |\n", finalStats[1][0], finalStats[1][1]);
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfGames = 0;
        int userWins = 0;
        int computerWins = 0;

        try {
            System.out.print("Enter the number of games to play: ");
            numberOfGames = scanner.nextInt();
            if (numberOfGames <= 0) {
                System.out.println("The number of games must be a positive integer.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        } finally {
            scanner.close();
        }

        String[][] gameResults = new String[numberOfGames][3];

        for (int i = 0; i < numberOfGames; i++) {
            System.out.println("\nGame " + (i + 1));
            System.out.print("Enter your choice (" + ROCK + ", " + PAPER + ", " + SCISSORS + "): ");
            String userChoice = scanner.next().toLowerCase();

            while (!userChoice.equals(ROCK) && !userChoice.equals(PAPER) && !userChoice.equals(SCISSORS)) {
                System.out.print("Invalid choice. Please enter 'rock', 'paper', or 'scissors': ");
                userChoice = scanner.next().toLowerCase();
            }

            String computerChoice = getComputerChoice();
            String winner = findWinner(userChoice, computerChoice);

            if (winner.equals("user")) {
                userWins++;
            } else if (winner.equals("computer")) {
                computerWins++;
            }

            gameResults[i][0] = userChoice;
            gameResults[i][1] = computerChoice;
            gameResults[i][2] = winner;
        }

        String[][] finalStats = calculateWinStats(userWins, computerWins, numberOfGames);
        displayGameResults(gameResults, finalStats);
    }
}
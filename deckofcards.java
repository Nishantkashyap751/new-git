import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class deckofcards {

    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int NUMBER_OF_CARDS = SUITS.length * RANKS.length;

    public static String[] initializeDeck() {
        String[] deck = new String[NUMBER_OF_CARDS];
        int cardIndex = 0;
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck[cardIndex] = rank + " of " + suit;
                cardIndex++;
            }
        }
        return deck;
    }

    public static String[] shuffleDeck(String[] deck) {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomCardIndex = i + random.nextInt(deck.length - i);

            String tempCard = deck[i];
            deck[i] = deck[randomCardIndex];
            deck[randomCardIndex] = tempCard;
        }
        return deck;
    }

    public static String[][] distributeCards(String[] shuffledDeck, int numPlayers, int cardsPerPlayer) {
        if (numPlayers * cardsPerPlayer > shuffledDeck.length) {
            throw new IllegalArgumentException("Not enough cards to distribute to all players.");
        }

        String[][] players = new String[numPlayers][cardsPerPlayer];
        int cardIndex = 0;
        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < cardsPerPlayer; j++) {
                players[i][j] = shuffledDeck[cardIndex];
                cardIndex++;
            }
        }
        return players;
    }

    public static void printPlayersHands(String[][] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("\nPlayer " + (i + 1) + " has the following cards:");
            for (String card : players[i]) {
                System.out.println("  - " + card);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 0;
        int cardsPerPlayer = 0;

        try {
            System.out.print("Enter the number of players (max 52): ");
            numPlayers = scanner.nextInt();
            if (numPlayers <= 0 || numPlayers > NUMBER_OF_CARDS) {
                System.out.println("Invalid number of players.");
                return;
            }

            System.out.print("Enter the number of cards per player: ");
            cardsPerPlayer = scanner.nextInt();
            if (cardsPerPlayer <= 0 || numPlayers * cardsPerPlayer > NUMBER_OF_CARDS) {
                System.out.println("Invalid number of cards per player. The total cards must not exceed 52.");
                return;
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        } finally {
            scanner.close();
        }

        String[] deck = initializeDeck();
        System.out.println("\nDeck of cards initialized.");

        String[] shuffledDeck = shuffleDeck(deck);
        System.out.println("Deck of cards shuffled.");

        String[][] playersHands = distributeCards(shuffledDeck, numPlayers, cardsPerPlayer);
        System.out.println("Cards distributed to " + numPlayers + " players.");

        printPlayersHands(playersHands);
    }
}
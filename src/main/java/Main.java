import java.util.Scanner;

/**
 * Main class to demonstrate the GuessingGame functionality.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to the Guessing Game!");
        while (playAgain) {
            DifficultyLevel difficulty = selectDifficulty(scanner);
            ScoringStrategy scoringStrategy = selectScoringStrategy(scanner);

            GuessingGame game = GuessingGameFactory.createGame(difficulty, scoringStrategy);
            System.out.println("\nGame started with " + difficulty + " difficulty!");

            while (!game.isGameOver()) {
                System.out.print("Enter your guess: ");
                String guess = scanner.nextLine();
                simulateGuess(game, guess);
            }

            System.out.println("Game Over! The correct number was: " + game.getCorrectNumber());
            System.out.println("Your final score: " + game.calculateScore(game.getGuessCount()));

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private static DifficultyLevel selectDifficulty(Scanner scanner) {
        System.out.println("Choose a difficulty: EASY, MEDIUM, HARD");
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            try {
                return DifficultyLevel.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. Please enter EASY, MEDIUM, or HARD.");
            }
        }
    }

    private static ScoringStrategy selectScoringStrategy(Scanner scanner) {
        System.out.println("Choose a scoring strategy: SIMPLE, TIME_BASED");
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("SIMPLE")) {
                return new SimpleScoring();
            } else if (input.equals("TIME_BASED")) {
                return new TimeBasedScoring();
            } else {
                System.out.println("Invalid choice. Please enter SIMPLE or TIME_BASED.");
            }
        }
    }

    /**
     * Simulates a guess and prints the outcome and current score.
     *
     * @param game  the GuessingGame instance
     * @param guess the guess to simulate
     */
    private static void simulateGuess(GuessingGame game, String guess) {
        try {
            double outcome = game.makeGuess(guess);
            String result;
            if (outcome == 0) {
                result = "Correct!";
            } else if (outcome == 3) {
                result = "Non-integer value.";
            } else if (outcome == 4) {
                result = "Duplicate guess!";
            } else if (outcome == 5 || outcome == 6) {
                result = "Game Over!";
            } else if (outcome < 3) {
                result = "Too low!";
            } else {
                result = "Too high!";
            }
            System.out.println("Guess: " + guess + " | Outcome: " + result);
            System.out.println("Current Score: " + game.calculateScore(game.getGuessCount()));
        } catch (GuessOutOfRangeException e) {
            System.out.println("Guess: " + guess + " | Exception: " + e.getMessage());
        }
    }

}

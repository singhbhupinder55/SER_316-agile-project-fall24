
/**
 * Main class to demonstrate the GuessingGame functionality.
 */
public class Main {

    /**
     * The main method, which serves as the entry point of the application.
     * It initializes a GuessingGame instance and makes a series of guesses to
     * test the game's behavior with valid, invalid, and out-of-range inputs.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        GuessingGame game = GuessingGameFactory.createGame(DifficultyLevel.MEDIUM, new SimpleScoring());

        System.out.println("Game started with Medium difficulty and SimpleScoring strategy.\n");

        // Simulating guesses
        simulateGuess(game, "50");
        simulateGuess(game, "35");
        simulateGuess(game, "abc");
        simulateGuess(game, "42");
        simulateGuess(game, "105");

        // Switch to TimeBasedScoring dynamically
        System.out.println("\nSwitching to TimeBasedScoring...\n");
        game.setScoringStrategy(new TimeBasedScoring());

        // Simulate additional guesses with TimeBasedScoring
        simulateGuess(game, "20");
        simulateGuess(game, "80");

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
            System.out.println("Guess: " + guess + " | Outcome: " + outcome);
            System.out.println("Score: " + game.calculateScore(game.getGuessCount()));
        } catch (GuessOutOfRangeException e) {
            System.out.println("Guess: " + guess + " | Exception: " + e.getMessage());
        }
    }
}

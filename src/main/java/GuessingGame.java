
import java.util.HashSet;
import java.util.Set;

/**
 * A game where players guess a number within a specific range based on difficulty.
 */
public class GuessingGame {

    private int correctNumber = 42;  // SER316 TASK 2 SPOT-BUGS FIX
    private Set<String> previousGuesses = new HashSet<>();
    private GameState gameState = new GameState();
    private ScoringStrategy scoringStrategy;
    private InputValidator validator = new InputValidator();




    /**
     * Sets the difficulty level for the game, which adjusts the range of numbers.
     *
     * @param difficulty the difficulty level (EASY, MEDIUM, HARD)
     */
    public void setDifficulty(DifficultyLevel difficulty) {
        int[] range = difficulty.getRange();
        correctNumber = generateRandomNumber(range[0], range[1]);
    }


    /**
     * Generates a random number within the specified range.
     *
     * @param lower the lower bound of the range
     * @param upper the upper bound of the range
     * @return a random number within the range
     */
    private int generateRandomNumber(int lower, int upper) {

        return lower + (int) (Math.random() * (upper - lower + 1));
    }

    /**
     * Sets the scoring strategy for the game.
     *
     * @param strategy the scoring strategy to use
     */
    public void setScoringStrategy(ScoringStrategy strategy) {
        if (strategy == null) {
            throw new IllegalStateException("Scoring strategy cannot be null.");
        }
        this.scoringStrategy = strategy;
    }

    /**
     * Calculates the score based on the number of guesses made and the strategy.
     *
     * @param guessesMade the number of guesses made
     * @return the calculated score
     */
    public int calculateScore(int guessesMade) {
        return scoringStrategy.calculateScore(guessesMade, correctNumber);
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    } // allows to set another number to guess

    /**
     * Gets the previous guesses made in the game.
     * @return a Set containing previous guesses.
     */
    public Set<String> getPreviousGuesses() {  // Getter method for previousGuesses
        return new HashSet<>(previousGuesses); // Return a new copy to protect the internal set
    }


    /**
     * Makes a guess and returns a code based on the outcome.
     * - 0: Correct guess
     * - 1.x: Too high, x is how far off the guess was, score reduces by x
     * - 2.x: Too low, x is how far off the guess was, score reduced by x
     * - 3.0: Non-integer value, score reduced by 3
     * - 4.0: Guess was already made
     * - 5.0: Game over after 10 incorrect guesses, should set flag accordingly
     * - 6.0: Guess made after game is over
     * The score is only set as described above.
     * The method should also change guessCount when appropriate and previousGuesses to keep track
     * of what has been guessed already. Only valid guesses will count toward these.
     *
     * @param guess the user's guess as a string
     * @return a double indicating the outcome of the guess
     * @throws GuessOutOfRangeException if the guess is outside the allowed range (1-100)
     */

    public double makeGuess(String guess) throws GuessOutOfRangeException {

        // Check if the game is over
        if (gameState.isGameOver()) {
            return 6.0; // Guess made after game is over
        }
        // Increment guess count for valid guesses
        gameState.incrementGuessCount();

        // Check if game should be marked as over after 10 incorrect guesses
        if (gameState.getGuessCount() > 10) {
            return handleGameOver();
        }

        int guessedNumber;

        // Handle non-integer input
        try {
            guessedNumber = validator.parseGuess(guess);
        } catch (NumberFormatException e) {
            gameState.updateScore(-3); // Reduce score for invalid input
            return 3.0; // Return 3.0 for non-integer value
        }

        validator.validateRange(guessedNumber, 1, 100);
        return handleOutcome(guessedNumber, guess);


    }


    /**
     * Handles the outcome of the guess.
     *
     * @param guessedNumber the parsed guessed number
     * @param guess         the original guess string
     * @return the outcome code
     */
    private double handleOutcome(int guessedNumber, String guess) {
        if (previousGuesses.contains(guess)) return 4.0;

        previousGuesses.add(guess);

        if (guessedNumber == correctNumber) {
            gameState.setGameOver(true);
            return 0.0; // Correct guess
        }

        int difference = Math.abs(correctNumber - guessedNumber);
        gameState.updateScore(-difference);
        return guessedNumber > correctNumber
                ? 1.0 + difference / 100.0
                : 2.0 + difference / 100.0;
    }

    /**
     * Handles the game over state.
     *
     * @return the outcome code for game over
     */
    private double handleGameOver() {
        gameState.setGameOver(true);
        return 5.0; // Game over after 10 guesses
    }



    /**
     * Resets the game state and clears previous guesses.
     */
    public void resetGame() {
        gameState.reset(); // Delegate the reset logic to GameState
        previousGuesses.clear(); // Clear previous guesses
    }



    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameState.getGameOver();
    }


    /**
     * Processes an array of guesses and returns the number of valid guesses made.
     *
     * @param guesses an array of guesses
     * @return the number of valid guesses
     */
    public int processValidGuesses(String[] guesses) {
        return validator.processValidGuesses(guesses, 1, 100);
    }


    /**
     * Calculates the average of a set of guesses.
     *
     * @param guesses a set of integer guesses
     * @return the average of the guesses as a Double.
     */
    public Double calculateAverage(Set<Integer> guesses) {
        return GameStatistics.calculateAverage(guesses);
    }

    /**
     * Gets the current count of guesses made in the game.
     *
     * @return the number of guesses made
     */
    public int getGuessCount() {
        return gameState.getGuessCount();
    }

    /**
     * Sets the game over state.
     *
     * @param gameOver true to mark the game as over, false otherwise.
     */
    public void setGameOver(boolean gameOver) {
        gameState.setGameOver(gameOver); // Delegate to GameState
    }

    /**
     * Gets the current game over state.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean getGameOver() {
        return gameState.getGameOver(); // Delegate to GameState
    }

    /**
     * Gets the correct number for the game.
     *
     * @return the correct number.
     */
    public int getCorrectNumber() {
        return correctNumber;
    }




}

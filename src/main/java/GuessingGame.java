
import java.util.HashSet;
import java.util.Set;

public class GuessingGame {

    private int correctNumber = 42;  // SER316 TASK 2 SPOT-BUGS FIX
    private Set<String> previousGuesses = new HashSet<>();
    private boolean gameOver = false; //SER316 TASK 2 SPOT-BUGS FIX
    private int guessCount = 0;
    private double score = 0; // game always starts at 0 points/score

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    } // allows to set another number to guess

    /**
     * Gets the previous guesses made in the game.
     * @return a Set containing previous guesses.
     */
    public Set<String> getPreviousGuesses() {  // Getter method for previousGuesses
        return previousGuesses;
    }

    /**
     * Gets the current count of guesses.
     * @return the count of guesses made.
     */
    public int getGuessCount() {  // Getter method for guessCount
        return guessCount;
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
        if (gameOver) {
            return 6.0; // Guess made after game is over
        }
        // Increment guess count for valid guesses
        guessCount++;

        // Check if game should be marked as over after 10 incorrect guesses
        if (guessCount > 10) {
            gameOver = true;
            return 5.0; // Return 5.0 when game is over after 10 incorrect guesses
        }

        // Check if the guess is non-integer
        int guessedNumber;
        try {
            guessedNumber = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            score -= 3; // Reduce score by 3 for non-integer value
            return 3.0; // Return 3.0 for non-integer input
        }

        // Check if the guessed number is out of range
        if (guessedNumber < 1 || guessedNumber > 100) {
            throw new GuessOutOfRangeException("Guess must be between 1 and 100.");
        }

        // Check if the guess was already made
        if (previousGuesses.contains(guess)) {
            return 4.0; // Return 4.0 for duplicate guess
        }

        // Add the guess to previous guesses
        previousGuesses.add(guess);

        // Check if the guess is correct
        if (guessedNumber == correctNumber) {
            gameOver = true; // Mark game as over only after checking for duplicates
            return 0.0; // Return 0.0 for correct guess
        }

        // Calculate the difference for too high or too low guesses
        int difference = Math.abs(correctNumber - guessedNumber);

        // Check if the guess is too high
        if (guessedNumber > correctNumber) {
            score -= difference; // Reduce score by the difference
            return 1.0 + difference / 100.0; // Return 1.x for too high
        } else {
            // Guess is too low
            score -= difference; // Reduce score by the difference
            return 2.0 + difference / 100.0; // Return 2.x for too low
        }


    }


    /**
     * Processes an array of guesses and returns the number of valid guesses made.
     * A valid guess is between 1 and 100, inclusive.
     *
     * @param guesses an array of guesses
     * @return the number of valid guesses
     */
    public int processValidGuesses(String[] guesses) {
        int validGuessCount = 0;
        for (String guess : guesses) {
            int guessNum = Integer.parseInt(guess);
            if (guessNum > 1 && guessNum <= 99) {
                validGuessCount++;
            }
        }
        return validGuessCount;
    }

    /**
     * Resets the game.
     */
    public void resetGame() {
        gameOver = false;
        guessCount = 0;
        previousGuesses.clear();
    }


    /**
     * Calculates the average of an array full of numbers.
     *
     * @param guesses a set of integer guesses
     * @return the average of the guesses as a Double.
     */
    public Double calculateAverage(Set<Integer> guesses) {
        if (guesses == null) {
            return null;
        }

        if (guesses.isEmpty()) {
            return 0.0; // Return 0.0 for an empty set as an improvement to handle empty input.
        }
        int sum = 0;

        for (Integer guess : guesses) {
            sum += guess;
        }
        return (double) sum / guesses.size();
    }

    public void setGameOver(Boolean isOver) {
        this.gameOver = isOver; // Simplified to directly set the gameOver flag based on input

    }

    public Boolean getGameOver() {
        return gameOver;  // Simplified to directly return the gameOver flag

    }

    public boolean isGameOver() { // SER316 TASK 2 SPOT-BUGS FIX
        return gameOver;
    }


}

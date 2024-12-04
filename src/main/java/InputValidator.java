/**
 * Validates user input for the guessing game.
 */
public class InputValidator {
    /**
     * Parses a string guess into an integer.
     * @param guess the user's guess as a string.
     * @return the parsed integer value.
     * @throws NumberFormatException if the guess is not a valid integer.
     */
    public int parseGuess(String guess) throws NumberFormatException {
        return Integer.parseInt(guess);
    }

    /**
     * Validates whether a guessed number is within the specified range.
     * @param guessedNumber the guessed number.
     * @param lower the lower bound of the range.
     * @param upper the upper bound of the range.
     * @throws GuessOutOfRangeException if the number is outside the valid range.
     */
    public void validateRange(int guessedNumber, int lower, int upper) throws GuessOutOfRangeException {
        if (guessedNumber < lower || guessedNumber > upper) {
            throw new GuessOutOfRangeException("Guess must be between " + lower + " and " + upper + ".");
        }
    }

    /**
     * Processes an array of guesses and returns the number of valid guesses made.
     * A valid guess is between the specified lower and upper bounds, inclusive.
     *
     * @param guesses an array of guesses
     * @param lower   the lower bound of the range
     * @param upper   the upper bound of the range
     * @return the number of valid guesses
     */
    public int processValidGuesses(String[] guesses, int lower, int upper) {
        int validGuessCount = 0;
        for (String guess : guesses) {
            try {
                int guessNum = parseGuess(guess);
                if (guessNum >= lower && guessNum <= upper) {
                    validGuessCount++;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid guesses
            }
        }
        return validGuessCount;
    }
}

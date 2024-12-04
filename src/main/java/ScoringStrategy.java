/**
 * Interface for different scoring strategies.
 */
public interface ScoringStrategy {

    /**
     * Calculates the score based on the guesses made and target number.
     *
     * @param guessesMade the number of guesses made
     * @param targetNumber the target number to guess
     * @return the calculated score
     */
    int calculateScore(int guessesMade, int targetNumber);
}

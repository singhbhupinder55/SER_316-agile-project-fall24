/**
 * Simple scoring strategy that deducts points for each incorrect guess.
 */
public class SimpleScoring implements ScoringStrategy {

    @Override
    public int calculateScore(int guessesMade, int targetNumber) {
        return Math.max(100 - (guessesMade * 10), 0);
    }
}

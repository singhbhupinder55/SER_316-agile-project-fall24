/**
 * Time-based scoring strategy that rewards points based on guessing speed.
 */
public class TimeBasedScoring implements ScoringStrategy {
    private long startTime;

    /**
     * Initializes the scoring strategy with the start time.
     */
    public TimeBasedScoring() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public int calculateScore(int guessesMade, int targetNumber) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        return Math.max(100 - (int) (elapsedTime / 1000), 0);
    }
}

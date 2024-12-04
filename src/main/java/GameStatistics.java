import java.util.Set;

/**
 * Utility class for game-related calculations.
 */
public class GameStatistics {

    /**
     * Calculates the average of an array full of numbers.
     *
     * @param guesses a set of integer guesses
     * @return the average of the guesses as a Double.
     */
    public static Double calculateAverage(Set<Integer> guesses) {
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
}

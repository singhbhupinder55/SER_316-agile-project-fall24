
import java.util.Map;

/**
 * Enum representing the difficulty levels of the game.
 */
public enum DifficultyLevel {
    EASY, MEDIUM, HARD;

    private static final Map<DifficultyLevel, int[]> ranges = Map.of(
            EASY, new int[]{1, 10},
            MEDIUM, new int[]{1, 50},
            HARD, new int[]{1, 100}
    );

    public int[] getRange() {
        return ranges.get(this);
    }

}


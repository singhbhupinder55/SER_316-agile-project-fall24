/**
 * Factory class for creating GuessingGame instances.
 */
public class GuessingGameFactory {

    /**
     * Creates a new GuessingGame with the specified difficulty and scoring strategy.
     * @param difficulty the difficulty level.
     * @param strategy the scoring strategy.
     * @return a configured GuessingGame instance.
     */
    public static GuessingGame createGame(DifficultyLevel difficulty, ScoringStrategy strategy) {
        GuessingGame game = new GuessingGame();
        game.setDifficulty(difficulty);
        game.setScoringStrategy(strategy);
        return game;
    }
}

/**
 * Represents the state of the game.
 */
public class GameState {
    private boolean gameOver = false;
    private int guessCount = 0;
    private double score = 0.0;

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets the game over state.
     * @param gameOver true to mark the game as over, false otherwise.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Gets the number of guesses made.
     * @return the guess count.
     */
    public int getGuessCount() {
        return guessCount;
    }

    /**
     * Increments the guess count by 1.
     */
    public void incrementGuessCount() {
        this.guessCount++;
    }

    /**
     * Gets the current score.
     * @return the score.
     */
    public double getScore() {
        return score;
    }

    /**
     * Updates the score by adding the given delta.
     * @param delta the change in score.
     */
    public void updateScore(double delta) {
        this.score += delta;
    }
    /**
     * Resets the game state to its initial values.
     */
    public void reset() {
        this.gameOver = false;
        this.guessCount = 0;
        this.score = 0.0;
    }

    /**
     * Gets the current game over state.
     * @return true if the game is over, false otherwise.
     */
    public boolean getGameOver() {
        return gameOver;
    }

}

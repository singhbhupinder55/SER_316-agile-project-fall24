import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class NumberGuessGameTest {

    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }


    // makeGuess method test cases
    @Test
    @DisplayName("TC1 - Correct Guess Test")
    void testCorrectGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(0.0, game.makeGuess("42"),
                "Expected correct guess to return 0.0");
    }

    @Test
    @DisplayName("TC2 - Too High Guess Test")
    void testTooHighGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(1.08, game.makeGuess("50"),
                "Expected too high guess to return 1.08");
    }

    @Test
    @DisplayName("TC3 - Too Low Guess Test")
    void testTooLowGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(2.12, game.makeGuess("30"),
                "Expected too low guess to return 2.12");
    }

    @Test
    @DisplayName("TC4 - Non-integer Guess Test")
    void testNonIntegerGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(3.0, game.makeGuess("abc"),
                "Expected non-integer input to return 3.0");
    }

    @Test
    @DisplayName("TC5 - Duplicate Correct Guess Test")
    void testDuplicateCorrectGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(50);
        game.makeGuess("42"); // First correct guess
        assertEquals(4.0, game.makeGuess("42"),
                "Duplicate correct guess should return 4.0");
    }

    @Test
    @DisplayName("TC6 - Out-of-Bounds Guess (Low)")
    void testGuessBelowValidRange() {
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("0"),
                "Guess below valid range should throw exception");
    }

    @Test
    @DisplayName("TC7 - Out-of-Bounds Guess (High)")
    void testGuessAboveValidRange() {
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("105"),
                "Guess above valid range should throw exception");
    }

    @Test
    @DisplayName("TC8 - Game Over After 10 Incorrect Guesses")
    void testGameOverAfter10IncorrectGuesses() throws GuessOutOfRangeException {
        game.setCorrectNumber(40);
        for (int i = 0; i < 10; i++) {
            game.makeGuess("72"); // Incorrect guesses
        }
        assertEquals(5.0, game.makeGuess("72"),
                "After 10 incorrect guesses, game should be over and return 5.0");
    }

    @Test
    @DisplayName("TC9 - Guess After Game Over")
    void testGuessAfterGameOver() throws GuessOutOfRangeException {
        game.setCorrectNumber(40);
        for (int i = 0; i < 10; i++) {
            game.makeGuess("40"); // Incorrect guesses to trigger game over
        }
        assertEquals(6.0, game.makeGuess("40"),
                "Guess after game over should return 6.0");
    }


    // processValidGuesses method tests
    /**
     * Test sequence for "All Valid Guesses" scenario.
     * This sequence achieves node coverage by ensuring all guesses are within range.
     */
    @Test
    @DisplayName("Test All Valid Guesses")
    void testAllValidGuesses() {
        String[] guesses = {"10", "20", "30", "40", "50"};
        assertEquals(5, game.processValidGuesses(guesses),
                "Expected all guesses to be counted as valid.");
    }

    /**
     * Test sequence for "All Invalid Guesses" scenario.
     * This sequence achieves node coverage by ensuring all guesses are out of range.
     */
    @Test
    @DisplayName("Test All Invalid Guesses (Out of Range)")
    void testAllInvalidGuesses() {
        String[] guesses = {"0", "101", "150", "-5", "200"};
        assertEquals(0, game.processValidGuesses(guesses),
                "Expected no guesses to be counted as valid.");
    }

    /**
     * Test sequence for "Mix of Valid and Invalid Guesses" scenario.
     * This sequence achieves edge coverage by including both valid and invalid guesses.
     */
    @Test
    @DisplayName("Test Mix of Valid and Invalid Guesses")
    void testMixOfValidAndInvalidGuesses() {
        String[] guesses = {"10", "0", "30", "150", "50"};
        assertEquals(3, game.processValidGuesses(guesses),
                "Expected only valid guesses to be counted.");
    }


    // tests for calculateAverage method
    /**
     * Tests for `calculateAverage` method.
     */

    @Test
    @DisplayName("Test calculateAverage with valid guesses")
    void testCalculateAverageWithValidGuesses() {
        Set<Integer> guesses = Set.of(10, 20, 30);
        assertEquals(20.0, game.calculateAverage(guesses),
                "Average of 10, 20, 30 should be 20.0");
    }

    @Test
    @DisplayName("Test calculateAverage with an empty set")
    void testCalculateAverageWithEmptySet() {
        Set<Integer> guesses = new HashSet<>();
        assertEquals(0.0, game.calculateAverage(guesses),
                "Average of an empty set should be 0.0");
    }

    @Test
    @DisplayName("Test calculateAverage with a single guess")
    void testCalculateAverageSingleGuess() {
        Set<Integer> guesses = Set.of(10);
        assertEquals(10.0, game.calculateAverage(guesses),
                "Average of a single guess should be the guess itself, 10.0");
    }

    @Test
    @DisplayName("Test calculateAverage with null input")
    void testCalculateAverageNullInput() {
        assertNull(game.calculateAverage(null),
                "Null input should return null");
    }


    // test's for resetGame method
    /**
     * Tests for `resetGame` method.
     */

    @Test
    @DisplayName("Test resetGame functionality")
    void testResetGame() throws GuessOutOfRangeException{
        game.setCorrectNumber(50);
        game.makeGuess("30"); // Make a guess to alter the state
        game.resetGame();

        // After reset, verify all values are set to initial states
        assertFalse(game.gameOver, "Game should not be over after reset");
        assertEquals(0, game.guessCount, "Guess count should be 0 after reset");
        assertTrue(game.previousGuesses.isEmpty(), "Previous guesses should be empty after reset");
    }

    // test's for setGameOver` and `getGameOver` methods.
    /**
     * Tests for `setGameOver` and `getGameOver` methods.
     */

    @Test
    @DisplayName("Test setGameOver and getGameOver with true")
    void testSetAndGetGameOverTrue() {
        game.setGameOver(true);
        assertTrue(game.getGameOver(), "Game should be over when set to true");
    }

    @Test
    @DisplayName("Test setGameOver and getGameOver with false")
    void testSetAndGetGameOverFalse() {
        game.setGameOver(false);
        assertFalse(game.getGameOver(), "Game should not be over when set to false");
    }
}
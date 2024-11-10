
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class GuessTest {

    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
        game.resetGame();
    }

    @Test
    @DisplayName("TC1 - Correct Guess Test")
    void testCorrectGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(0.0, game.makeGuess("42"), "Expected correct guess to return 0.0");
    }

    @Test
    @DisplayName("TC2 - Too High Guess Test")
    void testTooHighGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(1.08, game.makeGuess("50"), "Expected too high guess to return 1.08");
    }

    @Test
    @DisplayName("TC3 - Too Low Guess Test")
    void testTooLowGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(2.12, game.makeGuess("30"), "Expected too low guess to return 2.12");
    }

    @Test
    @DisplayName("TC4 - Non-integer Guess Test")
    void testNonIntegerGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        assertEquals(3.0, game.makeGuess("abc"), "Expected non-integer input to return 3.0");
    }

    @Test
    @DisplayName("TC5 - Duplicate Correct Guess Test")
    void testDuplicateCorrectGuess() throws GuessOutOfRangeException {
        game.setCorrectNumber(50);
        game.makeGuess("42"); // First correct guess
        assertEquals(4.0, game.makeGuess("42"), "Duplicate correct guess should return 4.0");
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



}

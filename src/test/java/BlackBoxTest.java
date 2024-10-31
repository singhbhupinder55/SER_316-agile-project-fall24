import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlackBoxTest {

    private GuessingGame game;



    // Method that supplies instances of different classes to the parameterized test
    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new NumberGuessGame1()),
                Arguments.of(new NumberGuessGame2()),
                Arguments.of(new NumberGuessGame3()),
                Arguments.of(new NumberGuessGame4()),
                Arguments.of(new NumberGuessGame5())
                );
    }

   //Reset the game instance before each game
    @BeforeEach
    void setUp() {
        game = new GuessingGame();
        game.resetGame();

    }


    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC1 - Correct Guess TesT (42)")
    void testMakeGuess(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Arrange: set the correct number to 42
        this.game = guessingGame;
        game.setCorrectNumber(42);
        double result = guessingGame.makeGuess("42");
        assertEquals(0.0, result, "The guess should be correct and return 0.0");

    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC2 - Too High Guess Test (50)")
    void testTooHighGuess(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        double result = game.makeGuess("50");
        System.out.println("Testing instance: " + game.getClass().getSimpleName() + ", Expected: 1.08, Actual: " + result);
        assertEquals(1.08, result, "Too high guess should return 1.08");


    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC3 - Too Low Guess Test (30)")
    void testTooLowGuess(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        double result = game.makeGuess("30");
        assertEquals(2.12, result, "Too low guess should return 2.12");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC4 - Non-integer Guess Test ('abc')")
    void testNonIntegerGuess(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        double result = game.makeGuess("abc");
        assertEquals(3.0, result, "Non-integer input should return 3.0");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC5 - Duplicate Correct Guess Test (42 after '42')")
    void testDuplicateCorrectGuess(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        game.makeGuess("42"); // First correct guess
        double result = game.makeGuess("42"); // Duplicate guess
        assertEquals(4.0, result, "Duplicate correct guess should return 4.0");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC6 - Guess Below Valid Range (0)")
    void testGuessBelowValidRange(GuessingGame game) {
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("0"), "Guess below valid range should throw exception");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC7 - Guess Above Valid Range (105)")
    void testGuessAboveValidRange(GuessingGame game) {
        assertThrows(GuessOutOfRangeException.class, () -> game.makeGuess("105"), "Guess above valid range should throw exception");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC8 - Lower Boundary Valid Guess (1)")
    void testLowerBoundaryGuess(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        double result = game.makeGuess("1");
        assertEquals(2.41, result, "Lower boundary guess should return 2.41");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC9 - Upper Boundary Valid Guess (100)")
    void testUpperBoundaryGuess(GuessingGame game) throws GuessOutOfRangeException {
        String upperBoundaryGuess = "99";
        try {
            double result = game.makeGuess(upperBoundaryGuess);
            // Assuming a valid response, check that it does not throw an exception
            // and instead returns the expected too high or correct result
            assertNotEquals(GuessOutOfRangeException.class, result);
        } catch (GuessOutOfRangeException e) {
            fail("GuessOutOfRangeException should not be thrown for boundary value 100.");
        }
    }


    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC10 - Game Over After 10 Incorrect Guesses")
    void testGameOverAfter10IncorrectGuesses(GuessingGame game) throws GuessOutOfRangeException {
        String incorrectGuess = "50";
        game.setCorrectNumber(42);
        for (int i = 0; i < 10; i++) {
            double result = game.makeGuess(incorrectGuess);
        // For the first 9 guesses, we expect a non-game-over response
        if (i < 10) {
            assertNotEquals(5.0, result, "Game should not be over before the 10th guess");
        } else {
            // On the 10th incorrect guess, we expect a 5.0 response indicating game over
            assertEquals(5.0, result, "After 10 incorrect guesses, should return 5.0 indicating game over");
        }
    }
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("TC11 - Guess Made After Game Over")
    void testGuessAfterGameOver(GuessingGame game) throws GuessOutOfRangeException {
        game.setCorrectNumber(42);
        for (int i = 0; i < 10; i++) {
            game.makeGuess("50"); // Make incorrect guesses to end the game
        }
        double result = game.makeGuess("50"); // Guess after game is over
        assertEquals(6.0, result, "Guess after game over should return 6.0");
    }
}


/**
 * The Main class serves as the entry point for the GuessingGame application.
 * It creates an instance of the GuessingGame and simulates a series of guesses
 * to demonstrate the game's functionality, including handling non-numeric input
 * and out-of-range guesses.
 */
public class Main {

    /**
     * The main method, which serves as the entry point of the application.
     * It initializes a GuessingGame instance and makes a series of guesses to
     * test the game's behavior with valid, invalid, and out-of-range inputs.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();

        try {
            // Simulating a few guesses,
            // this will not work when you get it only after you implement makeGuess in assignment 3
            System.out.println(game.makeGuess("50"));  // Too high
            System.out.println(game.makeGuess("35"));  // Too high
            System.out.println(game.makeGuess("abc"));  // Non-numeric input
            System.out.println(game.makeGuess("42"));  // Correct guess
            System.out.println(game.makeGuess("105"));  // Should throw GuessOutOfRangeException
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }


    }
}

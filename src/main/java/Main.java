public class Main {

    // Define constants for guess values
    private static final String TOO_HIGH_GUESS_1 = "50";
    private static final String TOO_HIGH_GUESS_2 = "35";
    private static final String NON_NUMERIC_GUESS = "abc";
    private static final String CORRECT_GUESS = "42";
    private static final String OUT_OF_RANGE_GUESS = "105";

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        String someValue;

        try {
            // Simulating a few guesses, this will not work when you get it only after you implement makeGuess in assignment 3
            someValue = TOO_HIGH_GUESS_1;
            System.out.println(game.makeGuess(someValue)); // Too high
            someValue = TOO_HIGH_GUESS_2;
            System.out.println(game.makeGuess(someValue));  // Too high
            someValue = NON_NUMERIC_GUESS;
            System.out.println(game.makeGuess(someValue));  // Non-numeric input
            someValue = CORRECT_GUESS;
            System.out.println(game.makeGuess(someValue));  // Correct guess
            someValue = OUT_OF_RANGE_GUESS;
            System.out.println(game.makeGuess(someValue));  // Should throw GuessOutOfRangeException
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }


        int numberGUESS;
    }
}

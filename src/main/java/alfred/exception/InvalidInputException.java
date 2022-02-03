package alfred.exception;

/**
 * Represents errors in user input for invalid input.
 */
public class InvalidInputException extends AlfredException {

    private static final String UNSURE = "OOPS!!! I'm sorry, "
            + "but I don't know what that means :-(";

    /**
     * Creates a new exception object for invalid input.
     */
    public InvalidInputException() {
        super(UNSURE);
    }
}

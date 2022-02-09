package alfred.exception;

/**
 * Represents errors in user input for lack of input.
 */
public class EmptyInputException extends AlfredException {

    private static final String NO_DESCRIPTION =
            "Please try again with a description in mind! Be as SPECIFIC as you can please...";

    /**
     * Creates a new exception object for empty input.
     *
     */
    public EmptyInputException() {
        super(NO_DESCRIPTION);
    }
}

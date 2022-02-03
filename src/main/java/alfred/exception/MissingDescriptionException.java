package alfred.exception;

/**
 * Represents errors in user input for missing description for tasks.
 */
public class MissingDescriptionException extends AlfredException {

    private static final String EMPTY = "I can't help you if you don't type anything ☹";

    /**
     * Creates a new exception object for missing description in tasks.
     */
    public MissingDescriptionException() {
        super(EMPTY);
    }
}

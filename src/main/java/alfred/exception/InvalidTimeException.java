package alfred.exception;

/**
 * Represents errors in user input for invalid date and time.
 */
public class InvalidTimeException extends AlfredException {

    private static final String INVALID_TIME =
            "Please try again with a valid time HHMM";

    /**
     * Creates a new exception object for invalid date.
     */
    public InvalidTimeException() {
        super(INVALID_TIME);
    }
}

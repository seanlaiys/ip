package alfred.exception;

/**
 * Represents errors in user input for invalid date and time.
 */
public class InvalidDateTimeException extends AlfredException {

    private static final String INVALID_DATETIME =
            "Please try again with a valid date and time DD/MM/YYYY HHMM";

    /**
     * Creates a new exception object for invalid date.
     */
    public InvalidDateTimeException() {
        super(INVALID_DATETIME);
    }
}

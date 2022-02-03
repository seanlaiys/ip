package alfred.exception;

/**
 *
 * Represents errors in user input for invalid date and time.
 *
 */
public class InvalidDateException extends AlfredException {

    private static final String INVALID_DATE = "Please try again with a valid date and time DD/MM/YYYY HHMM";

    /**
     * Creates a new exception object for invalid date.
     *
     */
    public InvalidDateException() {
        super(INVALID_DATE);
    }
}

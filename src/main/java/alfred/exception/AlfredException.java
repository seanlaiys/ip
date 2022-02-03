package alfred.exception;

/**
 *
 * Represents general errors in user input.
 *
 */
public class AlfredException extends Exception {

    /**
     * Creates a new Alfred exception object.
     *
     * @param msg error message
     */
    public AlfredException(String msg) {
        super(msg);
    }
}
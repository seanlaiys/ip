package alfred.exception;

public class InvalidCommandException extends AlfredException {
    private static final String DESCRIPTION =
            "It appears your command is invalid... Look up the user guide!";

    /**
     * Creates a new exception object for file not found.
     */
    public InvalidCommandException() {
        super(DESCRIPTION);
    }
}

package alfred.exception;

/**
 * Represents errors in user input for file not found.
 */
public class ExcessKeywordsException extends AlfredException {

    private static final String DESCRIPTION =
            "Too many words! Please use only one keyword.";

    /**
     * Creates a new exception object for file not found.
     */
    public ExcessKeywordsException() {
        super(DESCRIPTION);
    }
}

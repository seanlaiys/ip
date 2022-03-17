package alfred.exception;

/**
 * Represents errors in user input for file not found.
 */
public class FileNotFoundException extends AlfredException {

    private static final String DESCRIPTION =
            "It appears your file has been corrupted... Try deleting data there!";

    /**
     * Creates a new exception object for file not found.
     */
    public FileNotFoundException() {
        super(DESCRIPTION);
    }
}

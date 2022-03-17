package alfred.exception;

/**
 * Represents errors in user input for file not found.
 */
public class TaskNotFoundException extends AlfredException {

    private static final String DESCRIPTION =
            "It appears there is no task with such keyword... Try another keyword!";

    /**
     * Creates a new exception object for file not found.
     */
    public TaskNotFoundException() {
        super(DESCRIPTION);
    }
}

package alfred.exception;

public class TasksOutOfBoundsException extends AlfredException {
    private static final String ERR = "Please give me a valid task number on the list :(";

    /**
     * Creates a new exception object for missing description in tasks.
     */
    public TasksOutOfBoundsException() {
        super(ERR);
    }
}

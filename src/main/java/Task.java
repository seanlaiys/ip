import java.time.LocalDate;

/**
 *
 * This class encapsulates a Task and its status.
 *
 */
public abstract class Task {
    protected String task;
    protected boolean isDone;
    protected static final String DONE = "[X]";
    protected static final String NOTDONE = "[ ]";

    /**
     * Creates a new Task object
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks a Task object done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a Task object undone
     */
    public void unmark() {
        this.isDone = false;
    }

    public abstract LocalDate getDate();
}

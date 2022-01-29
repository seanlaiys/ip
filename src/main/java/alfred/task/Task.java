package alfred.task;

import java.time.LocalDate;
/**
 *
 * This class encapsulates a <code>Task</code> and its status.
 *
 */
public abstract class Task {
    protected String task;
    protected boolean isDone;
    protected static final String DONE = "[X]";
    protected static final String NOTDONE = "[ ]";

    /**
     * Creates a new <code>Task</code> object.
     *
     * @param task the task to be added
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Creates a new <code>Task</code> object.
     *
     * @param task the task to be added
     * @param isDone whether the task is done
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Marks a <code>Task</code> object done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a <code>Task</code> object undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    public abstract LocalDate getDate();
}

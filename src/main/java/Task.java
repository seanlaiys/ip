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

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

}

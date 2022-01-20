/**
 *
 * This class encapsulates a Task and its status.
 *
 */
public class Task {
    private String task;
    private boolean isDone;
    private static final String DONE = "[X]";
    private static final String NOTDONE = "[ ]";

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

    @Override
    public String toString() {
        String status = isDone ? DONE : NOTDONE;
        return status + " " + task;
    }
}

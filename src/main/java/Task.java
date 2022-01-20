/**
 *
 * This class encapsulates a Task and its status.
 *
 */
public class Task {
    private String task;
    private String type;
    private String date;
    private boolean isDone;
    private static final String DONE = "[X]";
    private static final String NOTDONE = "[ ]";
    private static final String DEADLINE = "[D]";
    private static final String EVENT = "[E]";
    private static final String TODO = "[T]";

    public Task(String task, String type) {
        this.task = task;
        this.isDone = false;
        this.type = type;
        this.date = "";
    }
    public Task(String task, String type, String date) {
        this.task = task;
        this.isDone = false;
        this.type = type;
        this.date = date;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String currentType = "";
        switch (this.type) {
            case Commands.COMMAND_DEADLINE:
                currentType = DEADLINE;
                break;
            case Commands.COMMAND_EVENT:
                currentType = EVENT;
                break;
            case Commands.COMMAND_TODO:
                currentType = TODO;
                break;
        }
        String status = isDone ? DONE : NOTDONE;
        return currentType + status + " " + task + "(" + this.date + ")";
    }
}

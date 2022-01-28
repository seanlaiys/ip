package alfred.task;

import java.time.LocalDate;
/**
 * Encapsulates a <code>alfred.task.ToDo</code> which inherits from <code>alfred.task.Task</code>.
 */
public class ToDo extends Task {
    private static final String TYPE = "[T]";

    /**
     * Creates a new alfred.task.ToDo object.
     *
     * @param toDo a description of the event
     */
    public ToDo(String toDo) {
        super(toDo);
    }

    /**
     * Creates a new alfred.task.ToDo object.
     *
     * @param toDo a description of the todo
     * @param isDone whether the todo is done
     */
    public ToDo(String toDo, boolean isDone) {
        super(toDo, isDone);
    }

    /**
     * Getter for the deadline date.
     *
     * @return a date of the deadline
     */
    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Returns a string representation of the alfred.task.ToDo object.
     *
     * @return a string describing the status and todo
     */
    @Override
    public String toString() {
        String status = isDone ? Task.DONE : Task.NOTDONE;
        return TYPE + status + " " + task;
    }
}

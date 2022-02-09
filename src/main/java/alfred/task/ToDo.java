package alfred.task;

import java.time.LocalDate;
/**
 * Encapsulates a ToDo which inherits from Task.
 */
public class ToDo extends Task {
    private static final String TYPE = "[T]";

    /**
     * Creates a new ToDo object.
     *
     * @param toDo a description of the event
     */
    public ToDo(String toDo) {
        super(toDo);
    }

    /**
     * Creates a new ToDo object.
     *
     * @param toDo a description of the todo
     * @param isDone whether the todo is done
     * @param priority int representing priority
     */
    public ToDo(String toDo, boolean isDone, int priority) {
        super(toDo, isDone, priority);
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
     * Returns a string representation of the ToDo object.
     *
     * @return a string describing the status and todo
     */
    @Override
    public String toString() {
        String status = isDone ? Task.DONE : Task.NOT_DONE;
        String priority = super.numToPriority();
        return TYPE + status + " " + task + priority;
    }
}

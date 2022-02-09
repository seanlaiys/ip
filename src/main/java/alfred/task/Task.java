package alfred.task;

import java.time.LocalDate;
/**
 *
 * This class encapsulates a Task and its status.
 *
 */
public abstract class Task implements Comparable<Task> {
    public static final String HIGH_PRIORITY = "Priority: !!!";
    public static final String MEDIUM_PRIORITY = "Priority: !!";
    public static final String LOW_PRIORITY = "Priority: !";
    protected static final String DONE = "[X]";
    protected static final String NOT_DONE = "[ ]";
    protected int priority;
    protected String task;
    protected boolean isDone;

    /**
     * Creates a new Task object.
     *
     * @param task the task to be added
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Creates a new Task object.
     *
     * @param task the task to be added
     * @param isDone whether the task is done
     */
    public Task(String task, boolean isDone, int priority) {
        this.task = task;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Marks a Task object done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a Task object undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Sets priority of task.
     *
     * @param p int representing priority
     */
    public void setPriority(int p) {
        priority = p;
    }

    /**
     * Returns whether the task contains a given keyword.
     *
     * @param keyword keyword input by user
     * @return a boolean of whether the task contains the keyword
     */
    public boolean contains(String keyword) {
        return task.contains(keyword);
    }

    /**
     * Returns string representing priority of a task.
     *
     * @return string representing priority
     */
    public String numToPriority() {
        switch (priority) {
        case 1:
            return " " + LOW_PRIORITY;
        case 2:
            return " " + MEDIUM_PRIORITY;
        case 3:
            return " " + HIGH_PRIORITY;
        default:
            return "";
        }
    }

    /**
     * Returns int representing priority of a task.
     *
     * @param priority string representing priority
     * @return int representing priority
     */
    public int priorityToNum(String priority) {
        switch (priority) {
        case LOW_PRIORITY:
            return 1;
        case MEDIUM_PRIORITY:
            return 2;
        case HIGH_PRIORITY:
            return 3;
        default:
            return 0;
        }
    }

    /**
     * Overrides the compareTo() method to compare priority of tasks.
     *
     * @param task task to compare priority with
     * @return 1 for greater priority, 0 for equal priority and -1 for equal priority
     */
    @Override
    public int compareTo(Task task) {
        if (priority > task.priority) {
            return 1;
        } else if (priority < task.priority) {
            return -1;
        } else if (isDone && !task.isDone) {
            return -1;
        } else if (!isDone && task.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public abstract LocalDate getDate();
}

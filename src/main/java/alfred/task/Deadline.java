package alfred.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Encapsulates a Deadline which inherits from Task.
 * Contains date, time and type.
 */
public class Deadline extends Task {
    private static final String TYPE = "[D]";
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a new Deadline object.
     *
     * @param deadline a description of the deadline
     * @param date a date of the deadline
     * @param time time of the deadline
     */
    public Deadline(String deadline, LocalDate date, LocalTime time) {
        super(deadline);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a new Deadline object.
     *
     * @param deadline a description of the deadline
     * @param date a date of the deadline
     * @param time time of the deadline
     * @param isDone whether the deadline is done
     * @param priority int representing priority
     */
    public Deadline(String deadline, LocalDate date, LocalTime time, boolean isDone, int priority) {
        super(deadline, isDone, priority);
        this.date = date;
        this.time = time;
    }


    /**
     * Getter for the deadline date.
     *
     * @return a date of the deadline
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string describing the status, the deadline and the date
     */
    @Override
    public String toString() {
        String status = isDone ? Task.DONE : Task.NOT_DONE;
        String priority = super.numToPriority();
        return TYPE + status + " " + task + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")" + priority;
    }
}

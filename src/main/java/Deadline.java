import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Encapsulates a <code>Deadline</code> which inherits from <code>Task</code>. Contains date, time and type.
 */
public class Deadline extends Task{
    private LocalDate date;
    private LocalTime time;
    private static final String TYPE = "[D]";

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
        String status = isDone ? DONE : NOTDONE;
        return TYPE + status + " " + task + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a"))+ ")";
    }
}
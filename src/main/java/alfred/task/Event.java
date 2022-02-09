package alfred.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Encapsulates an Event which inherits from Task.
 * Contains date, time and type.
 */
public class Event extends Task {
    private static final String TYPE = "[E]";
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a new Event object.
     *
     * @param event a description of the event.
     * @param date a date of the event
     */
    public Event(String event, LocalDate date, LocalTime time) {
        super(event);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a new Event object.
     *
     * @param event a description of the event.
     * @param date a date of the event
     * @param isDone whether the event is done
     * @param priority int representing priority
     */
    public Event(String event, LocalDate date, LocalTime time, boolean isDone, int priority) {
        super(event, isDone, priority);
        this.date = date;
        this.time = time;
    }

    /**
     * Getter for the event date.
     *
     * @return a date of the event
     */
    @Override
    public LocalDate getDate() {
        return date;
    }


    /**
     * Returns a string representation of the Event object.
     *
     * @return a string describing the status, the event and the date
     */
    @Override
    public String toString() {
        String status = isDone ? Task.DONE : Task.NOT_DONE;
        String priority = super.numToPriority();
        return TYPE + status + " " + task + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")" + priority;
    }
}

public class Event extends Task {

    private String date;
    private static final String TYPE = "[E]";

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : NOTDONE;
        return TYPE + status + " " + task + "(" + this.date + ")";
    }
}

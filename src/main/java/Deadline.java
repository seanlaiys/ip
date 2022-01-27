public class Deadline extends Task{
    private String date;
    private static final String TYPE = "[D]";

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : NOTDONE;
        return TYPE + status + " " + task + "(" + this.date + ")";
    }
}

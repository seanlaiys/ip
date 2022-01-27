public class ToDo extends Task{
    private static final String TYPE = "[T]";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : NOTDONE;
        return TYPE + status + " " + task;
    }
}

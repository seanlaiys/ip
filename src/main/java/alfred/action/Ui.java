package alfred.action;

import alfred.task.Task;
/**
 * Contains methods which prints messages Alfred could send
 * to the users according to what the users input.
 */
public class Ui {
    private static final String LOGO =
            "    __               _______\n"
            + "   /  \\              ||       ||\n"
            + "  / /\\ \\            ||____//\n"
            + " / /__\\ \\          || \n"
            + "/_/    \\_\\   O   ||        O\n";

    private static final String GREETING =
            "Greetings, my name is Alfred. \n" + "How may I assist you?";

    private static final String LIST = "Here is your list.";

    private static final String BLAH = "Blah.";

    private static final String BYE = "It has been great pleasure to be at your service. Goodbye.";

    private static final String ADD = "Got it. I've added this task:";

    private static final String DELETE = "Got it. I've removed this task:";

    private static final String MARK = "You have successfully completed the following task:";

    private static final String UNMARK = "You requested to mark the following task as undone:";

    private static final String TASK_SIZE = "Here is the number of tasks in the list:";

    private static final String PRIORITY = "I have adjusted the priority of the following task:";

    private static String output;

    /**
     * Creates new Ui object.
     */
    public Ui() {
        this.output = "";
    }

    /**
     * Updates output with logo and greeting words.
     */
    public static String greetUser() {
        return GREETING + "\n" + LOGO;
    }

    /**
     * Updates output with list of current tasks.
     *
     * @param currentTasks the current list of tasks
     */
    public static void generateList(TaskList currentTasks) {
        output = LIST + "\n" + currentTasks.toString();
    }

    /**
     * Updates output with Blah.
     */
    public static void sayBlah() {
        output = BLAH;
    }

    /**
     * Updates output with the words to signal successful
     * adding of task and returns the next input.
     *
     * @param newTask      the new task added
     * @param currentTasks the current list of tasks
     */
    public static void sayAdd(Task newTask, TaskList currentTasks) {
        String size = String.valueOf(currentTasks.getSize());
        output = ADD + "\n" + newTask.toString() + "\n" + TASK_SIZE + " " + size;
    }

    /**
     * Updates output with words to signal successful marking
     * of task and returns the next input.
     *
     * @param task the task unmarked
     */
    public static void sayMark(Task task) {
        output = MARK + "\n" + task.toString();
    }

    /**
     * Updates output with words to signal successful unmarking
     * of task and returns the next input.
     *
     * @param task the task unmarked
     */
    public static void sayUnmark(Task task) {
        output = UNMARK + "\n" + task.toString();
    }

    /**
     * Updates output with the words to signal successful deleting of task.
     *
     * @param task        the task deleted
     * @param currentTasks the current list of tasks
     */
    public static void sayDelete(Task task, TaskList currentTasks) {
        String size = String.valueOf(currentTasks.getSize());
        output = DELETE + "\n" + task.toString() + "\n" + TASK_SIZE + " " + size;
    }

    /**
     * Updates output with words to indicate uncertainty in user's input.
     */
    public static void showError(String error) {
        output = error;
    }

    /**
     * Updates output with Warning.
     */
    public static void sayPriority(Task task) {
        output = PRIORITY + "\n" + task.toString();
    }

    /**
     * Updates output with Goodbye.
     */
    public static void sayGoodbye() {
        output = BYE;
    }

    /**
     * Returns currently stored string to reply user.
     *
     * @return current output to print to user.
     */
    public static String getOutput() {
        return output;
    }
}

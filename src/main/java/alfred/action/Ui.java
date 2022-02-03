package alfred.action;

import java.util.Scanner;

import alfred.task.Task;
/**
 * Contains methods which prints messages Alfred could send
 * to the users according to what the users input.
 */
public class Ui {
    private static final String LOGO =
            "    __      \n"
            + "   /  \\    \n"
            + "  / /\\ \\  \n"
            + " / /__\\ \\  \n"
            + "/_/    \\_\\ \n";

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

    private static final String WARNING = "Do be careful with your input or you have to re-run me again";

    /**
     * Prints logo and greeting words.
     */
    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    /**
     * Prints list of current tasks and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @param currentTasks the current list of tasks
     * @return              the next input from the user
     */
    public static String generateList(Scanner userInput, TaskList currentTasks) {
        System.out.println(LIST);
        System.out.println(currentTasks.toString());
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints Blah and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @return           the next input from the user
     */
    public static String sayBlah(Scanner userInput) {
        System.out.println(BLAH);
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints the words to signal successful adding of task and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @param newTask      the new task added
     * @param currentTasks the current list of tasks
     * @return              the next input from the user
     */
    public static String sayAdd(Scanner userInput, Task newTask, TaskList currentTasks) {
        System.out.println(ADD);
        System.out.println(newTask.toString());
        System.out.println(TASK_SIZE + " " + currentTasks.getSize());
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints the words to signal successful marking of task and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @param task        the task marked
     * @return            the next input from the user
     */
    public static String sayMark(Scanner userInput, Task task) {
        System.out.println(MARK);
        System.out.println(task);
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints the words to signal successful unmarking of task and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @param task       the task unmarked
     * @return           the next input from the user
     */
    public static String sayUnmark(Scanner userInput, Task task) {
        System.out.println(UNMARK);
        System.out.println(task);
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints the words to signal successful deleting of task and returns the next input.
     *
     * @param userInput the Scanner for users to key in more inputs
     * @param task        the task deleted
     * @param currentTasks the current list of tasks
     * @return            the next input from the user
     */
    public static String sayDelete(Scanner userInput, Task task, TaskList currentTasks) {
        System.out.println(DELETE);
        System.out.println(task.toString());
        System.out.println(TASK_SIZE + " " + currentTasks.getSize());
        String input = userInput.nextLine();
        return input;
    }

    /**
     * Prints words to indicate uncertainty in user's input.
     */
    public static void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints words to indicate uncertainty in user's input.
     */
    public static void showWarning() {
        System.out.println(WARNING);
    }

    /**
     * Prints goodbye words.
     */
    public static void sayGoodbye() {
        System.out.println(BYE);
    }
}

import java.util.Scanner;

/**
 *
 * Contains methods which prints messages Alfred could send
 * to the users according to what the users input
 *
 */
public class Messages {
    private static final String LOGO =
            "    __      \n"
            + "   /  \\    \n"
            + "  / /\\ \\  \n"
            + " / /__\\ \\  \n"
            + "/_/    \\_\\ \n";

    private static final String GREETING =
            "Greetings, my name is Alfred. \n" + "How may I assist you?";

    private static final String LIST = "Here is your task list.";

    private static final String BLAH = "Blah.";

    private static final String BYE = "It has been great pleasure to be at your service. Goodbye.";

    private static final String ADD = "Got it. I've added this task:";

    private static final String DELETE = "Got it. I've removed this task:";

    private static final String MARK = "You have successfully completed the following task:";

    private static final String UNMARK = "You requested to mark the following task as undone:";

    private static final String TASK_SIZE = "Here is the number of tasks in the list:";

    private static final String UNSURE = "☹ OOPS!!! I'm sorry, " +
            "but I don't know what that means :-(";

    private static final String EMPTY = "I can't help you if you don't type anything ☹";

    private static final String NO_DESCRIPTION = "Please try again with a description in mind ☹";

    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    public static String generateList(Scanner user_input, TaskList currentTasks) {
        System.out.println(LIST);
        System.out.println(currentTasks.toString());
        String input = user_input.nextLine();
        return input;
    }

    public static String sayBlah(Scanner user_input) {
        System.out.println(BLAH);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayAdd(Scanner user_input, Task newTask, TaskList currentTasks) {
        System.out.println(ADD);
        System.out.println(newTask.toString());
        System.out.println(TASK_SIZE + " " + currentTasks.getSize());
        String input = user_input.nextLine();
        return input;
    }

    public static String sayMark(Scanner user_input, Task task) {
        System.out.println(MARK);
        System.out.println(task);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayUnmark(Scanner user_input, Task task) {
        System.out.println(UNMARK);
        System.out.println(task);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayDelete(Scanner user_input, Task task, TaskList currentTasks) {
        System.out.println(DELETE);
        System.out.println(task.toString());
        System.out.println(TASK_SIZE + " " + currentTasks.getSize());
        String input = user_input.nextLine();
        return input;
    }

    public static void sayUnsure() {
        System.out.println(UNSURE);
    }

    public static void sayEmpty() {
        System.out.println(EMPTY);
    }

    public static void sayNoDescription() {
        System.out.println(NO_DESCRIPTION);
    }

    public static void sayGoodbye() {
        System.out.println(BYE);
    }
}

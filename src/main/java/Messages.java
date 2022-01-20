import java.util.Scanner;

/**
 *
 * This class contains methods which prints messages Alfred could send
 * to the users according to what the users input
 *
 */
public class Messages {
    private static final String logo =
            "    __      \n"
                    + "   /  \\    \n"
                    + "  / /\\ \\  \n"
                    + " / /__\\ \\  \n"
                    + "/_/    \\_\\ \n";

    private static final String greetings =
            "Greetings, my name is Alfred. \n"
                    + "How may I assist you?";

    private static final String list = "Here is your list.";

    private static final String blah = "Blah.";

    private static final String bye = "It has been pleasant to be at your service. Goodbye.";

    private static final String add = "Successfully added:";

    private static final String mark = "You have successfully completed the following task:";

    private static final String unmark = "You requested to mark the following task as undone:";

    public static void greetUser() {
        System.out.println(logo);
        System.out.println(greetings);
    }

    public static String generateList(Scanner user_input, Storage currentList) {
        System.out.println(list);
        System.out.println(currentList.toString());
        String input = user_input.nextLine();
        return input;
    }

    public static String sayBlah(Scanner user_input) {
        System.out.println(blah);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayAdd(Scanner user_input, String text) {
        System.out.println(add + " " + text);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayMark(Scanner user_input, Task task) {
        System.out.println(mark);
        System.out.println(task);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayUnmark(Scanner user_input, Task task) {
        System.out.println(unmark);
        System.out.println(task);
        String input = user_input.nextLine();
        return input;
    }

    public static void sayGoodbye() {
        System.out.println(bye);
    }
}

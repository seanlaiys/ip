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

    public static void greetUser() {
        System.out.println(logo);
        System.out.println(greetings);
    }

    public static String generateList(Scanner user_input) {
        System.out.println(list);
        String input = user_input.nextLine();
        return input;
    }

    public static String sayBlah(Scanner user_input) {
        System.out.println(blah);
        String input = user_input.nextLine();
        return input;
    }

    public static void sayGoodbye() {
        System.out.println(bye);
    }
}

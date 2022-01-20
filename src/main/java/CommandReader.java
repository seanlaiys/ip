import java.util.Scanner;
/**
 *
 * This class serves to take in the input keyed in by the user
 * and invokes methods in response to what the user has keyed in.
 *
 */
public class CommandReader {
    public static void readCommand() {
        String input;
        Scanner user_input = new Scanner(System.in);
        input = user_input.nextLine();
        while(!input.equals(Commands.COMMAND_BYE)) {
            if(input.contains(Commands.COMMAND_LIST)) {
                input = Messages.generateList(user_input);
            }
            else if (input.contains(Commands.COMMAND_BLAH)) {
                input = Messages.sayBlah(user_input);
            }
            else {
                input = user_input.nextLine();
            }
        }
        Messages.sayGoodbye();
    }
}

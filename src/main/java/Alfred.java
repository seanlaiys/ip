/**
 *
 * Runs the Alfred chatbot.
 *
 */
public class Alfred{
    public static void main(String[] args) {
        Messages.greetUser();
        Parser.readCommand();
    }
}

/**
 *
 * Runs the Alfred chatbot. When <code>main()</code> is executed, chatbot greets users and invokes
 * <code>Parser.readCommand()</code> method.
 *
 */
public class Alfred{
    public static void main(String[] args) {
        Messages.greetUser();
        Parser.readCommand();
    }
}

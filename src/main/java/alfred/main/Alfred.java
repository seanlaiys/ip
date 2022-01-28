package alfred.main;

import alfred.exception.AlfredException;
import alfred.action.Parser;
import alfred.action.Ui;
/**
 *
 * Encapsulates the alfred.main.Alfred chatbot with a method to run it.
 * When <code>alfred.main()</code> is executed, chatbot greets users
 * and invokes <code>alfred.action.Parser.readCommand()</code> method.
 *
 */
public class Alfred{
    private Ui userInterface;
    private Parser parser;

    /**
     * Creates a new alfred.main.Alfred object.
     */
    public Alfred() {
        this.userInterface = new Ui();
        this.parser = new Parser();
    }

    /**
     * Reruns the command parse with warning.
     */
    private void reRunWithWarning() {
        try {
            parser.parseCommand();
        } catch (AlfredException e) {
            userInterface.showError(e.getMessage());
            userInterface.showWarning();
            reRunFinal();
        }
    }

    /**
     * Reruns the command parse before exiting with another error.
     */
    private void reRunFinal() {
        try {
            parser.parseCommand();
        } catch (AlfredException e) {
            userInterface.showError(e.getMessage());
            userInterface.sayGoodbye();
        }
    }

    /**
     * Invokes the parser and alfred.action.UI that runs the alfred.main.Alfred chatbot.
     */
    public void run() {
        userInterface.greetUser();
        try {
            parser.parseCommand();
        } catch (AlfredException e) {
            userInterface.showError(e.getMessage());
            reRunWithWarning();
        }
    }

    /**
     * This method executes the run method which runs the alfred.main.Alfred chatbot.
     */
    public static void main(String[] args) {
        new Alfred().run();
    }
}

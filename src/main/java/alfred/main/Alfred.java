package alfred.main;

import alfred.action.Parser;
import alfred.action.Ui;
import alfred.exception.AlfredException;
/**
 * Encapsulates the Alfred chatbot with a method to run it.
 * When main() is executed, chatbot greets users
 * and invokes Parser.readCommand() method.
 */
public class Alfred {
    private Ui userInterface;
    private Parser parser;
    private boolean hasGreeted;

    /**
     * Creates a new Alfred object.
     */
    public Alfred() {
        this.userInterface = new Ui();
        this.parser = new Parser();
        this.hasGreeted = false;
    }

    /**
     * Generates a response to user input.
     *
     * @param input input entered by user
     */
    public String getResponse(String input) {
        try {
            assert parser != null : "parser should not be equal to null";
            parser.parseCommand(input, userInterface);
        } catch (AlfredException e) {
            assert userInterface != null : "userInterface should not be equal to null";
            userInterface.showError(e.getMessage());
        }
        return userInterface.getOutput();
    }

    /**
     * Returns a boolean of whether Alfred has greeted.
     *
     * @return whether Alfred has greeted
     */
    public boolean isHasGreeted() {
        return hasGreeted;
    }

    /**
     * Sets that Alfred has greeted.
     */
    public void setGreeted() {
        hasGreeted = true;
    }

}

package alfred.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Alfred application.
     *
     * @param args the arguments keyed in by the user
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

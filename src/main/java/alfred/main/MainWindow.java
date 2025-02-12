package alfred.main;

import alfred.action.Commands;
import alfred.action.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alfred alfred;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image alfredImage =
            new Image(this.getClass().getResourceAsStream("/images/AlfredPennyworth.jpg"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Alfred object.
     *
     * @param a an Alfred object
     */
    public void setAlfred(Alfred a) {
        alfred = a;
        if (!alfred.isHasGreeted()) {
            dialogContainer.getChildren().add(
                    DialogBox.getAlfredDialog(Ui.greetUser(), alfredImage)
            );
            alfred.setGreeted();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "input text should not be null";
        String response = alfred.getResponse(input);
        assert response != null : "response text should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlfredDialog(response, alfredImage)
        );
        if (input.equals(Commands.COMMAND_BYE)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(done -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}

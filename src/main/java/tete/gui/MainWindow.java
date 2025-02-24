package tete.gui;

import javafx.stage.Stage;
import tete.Message;
import tete.Tete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Controller for the main GUI.
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

    private Tete tete;
    private final Message message = new Message();

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.jpg")));
    private final Image teteImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/tete.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTeteDialog(
               """
               Greetings, I'm Tete.
               How may I be of service to you?
               Currently, I appear to be a tracker of deadlines, events, and tasks to be done.
               (Note: Dates and times are entered in the format yyyy-mm-dd)
               """, teteImage));
    }

    /** Injects the Tete instance */
    public void setTete(Tete d) {
        tete = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tete's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = tete.getResponse(input);
        response = response.equals("bye") ? message.getFarewell() : response;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTeteDialog(response, teteImage)
        );
        userInput.clear();
        if (response.equals(message.getFarewell())) {
            closeApplication();
        }
    }

    private void closeApplication() {
        Stage currentStage = (Stage) dialogContainer.getScene().getWindow();
        currentStage.close();
    }
}


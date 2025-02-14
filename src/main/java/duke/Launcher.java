package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert args != null : "Application arguments should not be null";
        Application.launch(Tete.class, args);
    }
}
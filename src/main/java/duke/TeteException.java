package duke;

/** Represents all exceptions unique to Tete. */
public class TeteException extends RuntimeException {

    public TeteException(String message, Throwable cause) {
        super(message, cause);
    }
    public TeteException(String message) {
        super(message);
    }
}

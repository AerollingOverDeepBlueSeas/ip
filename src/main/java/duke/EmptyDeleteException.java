package duke;

/** Represents exception caused when no index is provided for deletion */
public class EmptyDeleteException extends TeteException {

    /** Creates a new EmptyDeleteException. */
    public EmptyDeleteException() {
        super("\tNo index has been provided. " +
                "\n\tNo deletion can occur under these circumstances.");
    }

}

package tete;

/** Represents exception caused when no index is provided for deletion */
public class EmptyDeleteException extends TeteException {

    /** Creates a new EmptyDeleteException. */
    public EmptyDeleteException() {
        super("No index has been provided. " +
                "\nNo deletion can occur under these circumstances.");
    }

}

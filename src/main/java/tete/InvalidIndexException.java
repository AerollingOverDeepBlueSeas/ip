package tete;

/** Represents exception caused when no index is provided to a command that requires one. */
public class InvalidIndexException extends TeteException {

    /** Creates a new InvalidIndexException. */
    public InvalidIndexException() {
        super("""
                \tThere is no task with the specified index.\
                \tYou may want to run the command 'list' again.\
                \tFortunately, one of us here has the items remembered.""");
    }
}

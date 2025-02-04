package duke;

/** Represents exception caused when a field keyword is missing from a command that needs it. */
public class MissingFieldException extends TeteException {

    /** Creates a new MissingFieldException.
     *
     * @param component specifies the field keyword that is missing from the command.
     */
    public MissingFieldException(String component) {
        super("\tYour command seems to be missing the component " + component + "." +
                "\n\tPlease take note to include what is needed for each command." +
                "\n\tAfter all, what use is a reminder that cannot remind you of the most important information?");
    }
}

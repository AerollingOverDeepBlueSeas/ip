package duke;

/** Represents exception caused when some of the fields are missing from the command. */
public class MissingFieldContentsException extends TeteException {

    /** Creates a new MissingFieldContentsException.
     *
     * @param field specifies the field of the command that is missing contents.
     */
    public MissingFieldContentsException(String field) {
        super("\tYour command seems to be missing information for the following field(s): " + field + ".");
    }
}

package duke;

public class MissingFieldContentsException extends TeteException {
    public MissingFieldContentsException(String field) {
        super("\tYour command seems to be missing information for the following field(s): " + field + ".");
    }
}

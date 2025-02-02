package duke;

public class MissingFieldException extends TeteException {
    public MissingFieldException(String component) {
        super("\tYour command seems to be missing the component " + component + "." +
                "\n\tPlease take note to include what is needed for each command." +
                "\n\tAfter all, what use is a reminder that cannot remind you of the most important information?");
    }
}

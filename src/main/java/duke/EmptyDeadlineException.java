package duke;

/** Represents exception caused when there is nothing entered to represent a deadline. */
public class EmptyDeadlineException extends TeteException {

    /** Creates a new EmptyDeadlineException. */
    public EmptyDeadlineException() {
        super("""
                \tSurely there was a deadline you wanted to meet?\
                \t...or did you simply wish to relish in the lack of actual deadlines?\
                \tIf so, I recommend actually creating a deadline and striking it out.\
                \tIt tends to be more satisfying that way.\
                \t(Enter some text after the 'deadline' command)""");
    }
}

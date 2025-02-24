package tete;

/** Represents exception caused when there is nothing entered to represent a deadline. */
public class EmptyDeadlineException extends TeteException {

    /** Creates a new EmptyDeadlineException. */
    public EmptyDeadlineException() {
        super("""
                Surely there was a deadline you wanted to meet?\
                 ...or did you simply wish to relish in the lack of actual deadlines?\
                 If so, I recommend actually creating a deadline and striking it out.\
                 It tends to be more satisfying that way.\
                \n(Enter some text after the 'deadline' command)""");
    }
}

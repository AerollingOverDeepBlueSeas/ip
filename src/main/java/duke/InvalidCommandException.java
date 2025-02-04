package duke;

/** Represents exception caused when the command keyword is invalid. */
public class InvalidCommandException extends TeteException {

    /** Creates a new InvalidCommandException. */
    public InvalidCommandException() {
        super("""
                \tI am afraid I do not understand this command.\
                
                \tOf course, it is just as likely a result of my ignorance as it is your incompetence.\
                
                \t(Commands supported currently: todo, deadline, event, mark, unmark, delete, find, bye""");
    }
}

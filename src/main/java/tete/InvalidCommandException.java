package tete;

/** Represents exception caused when the command keyword is invalid. */
public class InvalidCommandException extends TeteException {

    /** Creates a new InvalidCommandException. */
    public InvalidCommandException() {
        super("""
                I am afraid I do not understand this command.\
                Of course, it is just as likely a result of my ignorance as it is your incompetence.\
                (Commands supported currently: todo, deadline, event, mark, unmark, delete, find, bye""");
    }
}

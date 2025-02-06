package duke;

/** Represents exception caused when there is nothing entered to represent an event. */
public class EmptyEventException extends TeteException {

    /** Creates a new EmptyEventException. */
    public EmptyEventException() {
        super("""
                \tSurely there was an event you need to attend?\
                \t...or did you simply wish to be invited to one?\
                \t(Enter some text after the 'event' command.)""");
    }
}

package tete;

/** Represents exception caused when there is nothing entered to represent an event. */
public class EmptyEventException extends TeteException {

    /** Creates a new EmptyEventException. */
    public EmptyEventException() {
        super("""
                Surely there was an event you need to attend?\
                ...or did you simply wish to be invited to one?\
                (Enter some text after the 'event' command.)""");
    }
}

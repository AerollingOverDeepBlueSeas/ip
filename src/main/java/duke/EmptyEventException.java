public class EmptyEventException extends TeteException {
    public EmptyEventException() {
        super("""
                \tSurely there was an event you need to attend?\
                
                \t...or did you simply wish to be invited to one?\
                
                \t(Enter some text after the 'event' command.)""");
    }
}

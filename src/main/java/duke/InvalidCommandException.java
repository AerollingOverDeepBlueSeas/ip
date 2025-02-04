package duke;

public class InvalidCommandException extends TeteException {
    public InvalidCommandException() {
        super("""
                \tI am afraid I do not understand this command.\
                
                \tOf course, it is just as likely a result of my ignorance as it is your incompetence.\
                
                \t(Commands supported currently: todo, deadline, event, mark, unmark, delete, find, bye""");
    }
}

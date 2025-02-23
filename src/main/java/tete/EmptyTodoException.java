package tete;

/** Represents exception caused when there is nothing entered to represent a todo. */
public class EmptyTodoException extends TeteException {

    /** Creates a new EmptyTodoException. */
    public EmptyTodoException() {
        super("""
                Surely there must be something you ought to do?\
                Or did you stumble upon this command on by accident?\
                (Enter some text after the 'todo' command.)""");
    }
}

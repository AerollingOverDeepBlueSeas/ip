package duke;

public class EmptyTodoException extends TeteException {
    public EmptyTodoException() {
        super("""
                \tSurely there must be something you ought to do?\
                
                \tOr did you stumble upon this command on by accident?\
                
                \t(Enter some text after the 'todo' command.)""");
    }
}

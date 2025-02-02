package duke;

public class InvalidIndexException extends TeteException {
    public InvalidIndexException() {
        super("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""");
    }
}

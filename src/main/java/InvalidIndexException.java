public class InvalidIndexException extends TeteException {
    public InvalidIndexException() {
        super("\tThere is no task with the specified index." +
                "\n\tYou may want to run the command 'list' again.");
    }
}

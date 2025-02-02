public class EmptyDeleteException extends TeteException {
    public EmptyDeleteException() {
        super("\tNo index has been provided. " +
                "\n\tNo deletion can occur under these circumstances.");
    }
}

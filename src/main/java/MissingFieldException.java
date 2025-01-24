public class MissingFieldException extends TeteException {
    public MissingFieldException(String component) {
        super("\tYour command seems to be missing the component " + component + ".");
    }
}

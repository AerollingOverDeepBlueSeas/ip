package duke;

/** Represents exception caused when the given date is invalid. */
public class InvalidDateException extends TeteException {

    /** Creates a new InvalidDateException. */
    public InvalidDateException() {
        super("""
                \tThere seems to be something amiss with the date(s) you have entered.\
                \tDo take care to enter it in the format yyyy-mm-dd.\
                \tThe minute details are important, after all.""");
    }
}

package tete;

/** Represents exception caused when the given date is invalid. */
public class InvalidDateException extends TeteException {

    /** Creates a new InvalidDateException. */
    public InvalidDateException() {
        super("""
                There seems to be something amiss with the date(s) you have entered.\
                Do take care to enter it in the format yyyy-mm-dd.\
                The minute details are important, after all.""");
    }
}

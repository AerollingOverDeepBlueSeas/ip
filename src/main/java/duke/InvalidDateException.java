package duke;

public class InvalidDateException extends TeteException {
    public InvalidDateException() {
        super("""
                \tThere seems to be something amiss with the date(s) you have entered.\
                
                \tDo take care to enter it in the format yyyy-mm-dd.\
                
                \tThe minute details are important, after all.""");
    }
}

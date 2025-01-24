public class EmptyDeadlineException extends TeteException {
    public EmptyDeadlineException() {
        super("""
                \tSurely there was a deadline you wanted to meet?\
                
                \t...or did you simply wish to relish in the lack of actual deadlines?\
                
                \tIf so, I recommend actually creating a deadline and striking it out.\
                
                \tIt tends to be more satisfying that way.\
                
                \t(Enter some text after the 'deadline' command)""");
    }
}

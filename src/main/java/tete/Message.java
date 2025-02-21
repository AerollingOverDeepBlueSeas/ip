package tete;

public class Message {

    public String getGreeting() {
        return """
                Greetings, I'm Tete.
                How may I be of service to you?
                Currently, I appear to be a tracker of deadlines, events, and tasks to be done.
                (Note: Dates and times are entered in the format yyyy-mm-dd)
                """;
    }

    public String getFarewell() {
        return "Farewell. May our paths cross again soon." +
                "\n...or not.";
    }

}

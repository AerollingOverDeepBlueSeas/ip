package tete;

/** Class that contains the main program */
public class Tete {

    private static final TaskList tasks = new TaskList();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();

    public Tete () {
        for (String entry : storage.readContents()) {
            tasks.addItemFromFile(entry);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return parser.parseCommand(input, tasks, storage);
        } catch (TeteException e) {
            return e.getMessage();
        }
    }

}

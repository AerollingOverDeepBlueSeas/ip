import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.Scanner;

public class Tete {

    private static final TaskList tasks = new TaskList();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();
    private static final UI ui = new UI();

    public static void main(String[] args) {

        String input = "";
        String[] inputs;

        ui.start();
        for (String entry : storage.readContents()) {
            tasks.addItemFromFile(entry);
        }

        while (!input.equalsIgnoreCase("bye")) {
            try {
                input = ui.acceptInput();
                parser.parseCommand(input, tasks, storage);
            } catch (TeteException e) {
                ui.displayErrorMessage(e);
            } finally {
                ui.line();
            }
        }

        ui.close();

    }
}

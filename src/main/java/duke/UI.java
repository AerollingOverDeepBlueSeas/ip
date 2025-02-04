package duke;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.Scanner;

/** Represents the user interface. */
public class UI {

    private static Scanner input;
    private static PrintWriter pw;

    String line = "\t⊱ ──── {⋆⌘⋆} ──── ⊰⊱ ────── {⋆⌘⋆} ────── ⊰⊱ ──── {⋆⌘⋆} ──── ⊰";
    String greeting = """
                    Greetings, I'm Tete.
                    How may I be of service to you?
                    Currently, I appear to be a tracker of deadlines, events, and tasks to be done.
                    (Note: Dates and times are entered in the format yyyy-mm-dd)
                """;
    String farewell = "\tFarewell. May our paths cross again soon." +
            "\n\t...or not.";

    /** Creates a new UI that handles user input and output. */
    public UI () {
        input = new Scanner(System.in);
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    /** Print the initializing messages of Tete. */
    public void start() {
        pw.println(line);
        pw.println("\tAdding any existing record to list...");
        pw.println(line);
        pw.println(greeting);
        pw.println(line);
        pw.flush();
    }

    /** Receives a line of user input and returns it.
     *
     * @return the user input as a String
     */
    public String acceptInput() {
        return input.nextLine();
    }

    /** Displays error messages when encountered. */
    public void displayErrorMessage(Exception e) {
        pw.println(e.getMessage());
        pw.flush();
    }

    /** Prints separator line. */
    public void line() {
        pw.println();
        pw.println(line);
        pw.flush();
    }

    /** Prints closing message and closes input and output. */
    public void close() {
        pw.println(farewell);
        pw.println(line);
        pw.close();
        input.close();
    }

}

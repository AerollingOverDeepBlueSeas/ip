package tete;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

/** Represents the user interface. */
public class UI {

    private static Scanner input = new Scanner(System.in);
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

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

    /**
     * Receives a line of user input and returns it.
     *
     * @return the user input as a String
     */
    public String acceptInput() {
        return input.nextLine();
    }

    /** Returns message for successful execution of task adding. */
    public static String returnSuccessfulAddMessage(Task newTask, ArrayList<Task> tasks) {
        return "In accordance to your wishes, the following task has been added: " +
                "\n" + newTask +
                "\nYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks.");
    }

    /** Returns message for successful execution of task marking. */
    public static String returnSuccessfulMarkMessage(Task newTask) {
        return "In accordance to your wishes, the following task has been marked as completed." +
                "\n" + newTask;
    }

    /** Returns message for successful execution of task unmarking. */
    public static String returnSuccessfulUnmarkMessage(Task newTask) {
        return "In accordance to your wishes, the completion status of the following task has been revoked." +
                "\nPlease see to it that you complete it soon." +
                "\n" + newTask;
    }

    /** Returns message for successful execution of task deleting. */
    public static String returnSuccessfulDeleteMessage(ArrayList<Task> tasks, Task task) {
        return "In accordance to your wishes, the following task has been removed: " +
                "\n" + task +
                "\nYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks.");
    }

    /** Displays message for successful execution of task adding. */
    public static void displaySuccessfulAddMessage(Task newTask, ArrayList<Task> tasks) {
        pw.println("\tIn accordance to your wishes, the following task has been added: ");
        pw.println("\t  " + newTask);
        pw.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
        pw.flush();
    }

    /** Displays message for successful execution of task marking. */
    public static void displaySuccessfulMarkMessage(Task newTask) {
        System.out.println("\tIn accordance to your wishes, the following task has been marked as completed.");
        System.out.println("\t" + newTask);
        pw.flush();
    }

    /** Displays message for successful execution of task unmarking. */
    public static void displaySuccessfulUnmarkMessage(Task newTask) {
        System.out.println("\tIn accordance to your wishes, the completion status of the following task has been revoked." +
                "\n\tPlease see to it that you complete it soon.");
        System.out.println("\t" + newTask);
        pw.flush();
    }

    /** Displays message for successful execution of task deleting. */
    public static void displaySuccessfulDeleteMessage(ArrayList<Task> tasks, Task task) {
        System.out.println("In accordance to your wishes, the following task has been removed: ");
        System.out.println("\t  " + task);
        System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
        pw.flush();
    }

    /** Prints argument to the screen. */
    public static void displaySomeLine(String someLine) {
        pw.println(someLine);
        pw.flush();
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

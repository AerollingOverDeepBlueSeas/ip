package tete;

import java.util.ArrayList;

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

    public static String returnUnsuccessfulAddMessage(Task newTask) {
        return "This task has already been added to the list of tasks." +
                "\nSurely there must be a better approach to duplicating the same task?";
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

}

package duke;

/**
 * Represents a task.
 * Contains description, completion status, and formatted String to be written to file.
 */
public class Task {

    // TODO move error checking here

    protected String description;
    protected boolean isDone;
    /** Formatted String containing all information, to be written to file on program termination. */
    protected String data;

    /** Creates a task.
     * Assumes task to be incomplete by default.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Creates a task.
     * Takes on additional parameter to initialise completed tasks.
     * Only used to recover data from file on initialisation.
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /** Returns different string depending on completion status.
     * @return X if task is complete, and an empty space otherwise.
     */
    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    /** Marks task as done.
     * Currently, tasks already done can still be marked as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tIn accordance to your wishes, the following task has been marked as completed.");
        System.out.println("\t"+this.toString());
    }

    /** Marks task as not done.
     * Currently, tasks already done can still be marked as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("\tIn accordance to your wishes, the completion status of the following task has been revoked." +
                "\n\tPlease see to it that you complete it soon.");
        System.out.println("\t"+this.toString());
    }


    /** Returns formatted string representation of the task.
     * For saving to file on program termination.
     */
    public String toData() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }


    @Override
    public String toString() {
        return "["+this.getStatus()+"] " + this.description;
    }

}

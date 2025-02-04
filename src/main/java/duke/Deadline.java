package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents a deadline.
 * Contains a date for the deadline.
 */
public class Deadline extends Task{

    protected LocalDate deadline;

    /** Creates a new deadline. */
    public Deadline(String newItem, LocalDate deadline){
        super(newItem);
        this.deadline = deadline;
    }

    /** Creates a new deadline.
     * Takes on additional parameter to initialise completed tasks.
     * Only used to recover data from file on initialisation.
     */
    public Deadline(String newItem, LocalDate deadline, boolean done) {
        super(newItem, done);
        this.deadline = deadline;
    }

    @Override
    public String toData() {
        return "D | " + this.getStatus() + " | " + this.description + " | " + this.deadline;
    }

    @Override
    public String toString(){
        return "[D]["+this.getStatus()+"] " + this.description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 * Contains description, start date and end date.
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /** Creates a new event */
    public Event(String newItem, LocalDate start, LocalDate end) {
        super(newItem);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new event.
     * Takes on additional parameter to initialise completed tasks.
     * Only used to recover data from file on initialisation.
     */
    public Event(String newItem, LocalDate start, LocalDate end, boolean done) {
        super(newItem, done);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toData() {
        return "E | " + this.getStatus() + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + this.description + " (from: " +
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}

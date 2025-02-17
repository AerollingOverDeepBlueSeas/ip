package tete;

/** Represents an item to be done. */
public class Todo extends Task {

    /** Creates a new Todo */
    public Todo(String newItem) {
        super(newItem);
    }

    /**
     * Creates a new Todo.
     * Takes on additional parameter to initialise completed tasks.
     * Only used to recover data from file on initialisation.
     */
    public Todo(String newItem, boolean done) {
        super(newItem, done);
    }

    @Override
    public String toData() {
        return "T | " + this.getStatus() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatus() + "] " + this.description;
    }

}
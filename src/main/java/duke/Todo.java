public class Todo extends Task {

    public Todo(String newItem) {
        super(newItem);
    }

    public Todo(String newItem, boolean done) {
        super(newItem, done);
    }

    @Override
    public String toData() {
        return "T | " + this.getStatus() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]["+this.getStatus()+"] " + this.description;
    }

}
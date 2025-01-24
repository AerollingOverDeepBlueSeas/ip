public class Todo extends Task {

    public Todo(String newItem) {
        super(newItem);
    }

    @Override
    public String toString() {
        return "[T]["+this.getStatus()+"] " + this.description;
    }

}
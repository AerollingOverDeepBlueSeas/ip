public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String newItem, String start, String end) {
        super(newItem);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]["+this.getStatus()+"] " + this.description + " (from: " + this.start + " to: " + this.end + ")";
    }

}

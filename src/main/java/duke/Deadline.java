import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate deadline;

    public Deadline(String newItem, LocalDate deadline){
        super(newItem);
        this.deadline = deadline;
    }

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

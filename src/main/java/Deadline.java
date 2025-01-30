public class Deadline extends Task{

    protected String deadline;

    public Deadline(String newItem, String deadline){
        super(newItem);
        this.deadline = deadline;
    }

    public Deadline(String newItem, String deadline, boolean done) {
        super(newItem, done);
        this.deadline = deadline;
    }

    @Override
    public String toData() {
        return "D | " + this.getStatus() + " | " + this.description + " | " + this.deadline;
    }

    @Override
    public String toString(){
        return "[D]["+this.getStatus()+"] " + this.description + " (by: " + this.deadline + ")";
    }

}

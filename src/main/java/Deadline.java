public class Deadline extends Task{

    protected String deadline;

    public Deadline(String newItem, String deadline){
        super(newItem);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]["+this.getStatus()+"] " + this.description + " (by: " + this.deadline + ")";
    }

}

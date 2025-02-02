package duke;

public class Task {

    // TODO move error checking here

    protected String description;
    protected boolean isDone;
    protected String data;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tIn accordance to your wishes, the following task has been marked as completed.");
        System.out.println("\t"+this.toString());
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("\tIn accordance to your wishes, the completion status of the following task has been revoked." +
                "\n\tPlease see to it that you complete it soon.");
        System.out.println("\t"+this.toString());
    }

    public String toData() {
        return "";
    }

    @Override
    public String toString() {
        return "["+this.getStatus()+"] " + this.description;
    }

}

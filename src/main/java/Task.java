
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return "["+this.getStatus()+"] " + this.description;
    }

}

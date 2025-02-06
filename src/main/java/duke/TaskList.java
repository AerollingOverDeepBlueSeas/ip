package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/** Represents the list of tasks during the session. */
public class TaskList {

    private final ArrayList<Task> tasks;

    /** Creates a new TaskList. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the back of the list.
     *
     * @param newTask to be added to the list.
     */
    public void addItem(Task newTask) {
        tasks.add(newTask);
        UI.displaySuccessfulAddMessage(newTask, tasks);
    }

    /**
     * Removes a task from the list, given its position in the list.
     *
     * @param input String representation of the position of the task.
     * @throws InvalidIndexException when the input leads to an invalid index.
     */
    public void removeItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            System.out.println(tasks.size());
            UI.displaySuccessfulDeleteMessage(tasks, tasks.remove(index));
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    /** Prints all tasks to screen. */
    public void displayItems() {
        int index = 0;
        for (Task task : tasks) {
            UI.displaySomeLine("\t" + Integer.valueOf(++index).toString() + ". " + task.toString());
        }
    }

    /**
     * Adds item to list, given the formatted String representation of the task.
     * Constructs a new item from the data in the String, and adds it to the list.
     *
     * @param line containing contents of the task
     */
    public void addItemFromFile(String line) {
        String[] components = line.split(" \\| ");
        Task newTask = new Task("default");

        newTask = switch (components[0]) {
            case "T" ->
                // duke.Todo
                    new Todo(components[2], components[1].equals("X"));
            case "D" ->
                // duke.Deadline
                    new Deadline(components[2], LocalDate.parse(components[3]), components[1].equals("X"));
            case "E" ->
                // duke.Event
                    new Event(components[2], LocalDate.parse(components[3]),
                            LocalDate.parse(components[4]), components[1].equals("X"));
            default -> newTask;
        };

        tasks.add(newTask);

    }

    /**
     * Marks item specified to be completed.
     * A completed item can still be marked as completed again.
     *
     * @param input String representation of the position of the task.
     * @throws InvalidIndexException when the input leads to an invalid index.
     */
    public void markItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).markAsDone();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Marks item specified to be not completed.
     * An incomplete item can still be marked as incomplete again.
     *
     * @param input String representation of the position of the task.
     * @throws InvalidIndexException when the input leads to an invalid index.
     */
    public void unmarkItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).unmarkAsDone();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Checks whether a given index is valid, and returns it as an integer if valid.
     *
     * @param input String representation of the position of the task.
     * @return integer representing index in the list.
     * @throws InvalidIndexException when the input leads to an invalid index.
     */
    public int validateIndex(String input) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input) -1;
            if (index < 0 || index >= tasks.size()) {
                throw new InvalidIndexException();
            }
            return index;
        } catch (Exception e) {
            // Any exceptions caught here is caused by an invalid index
            throw new InvalidIndexException();
        }

    }

    /**
     * Converts the current list of tasks to ArrayList of strings of their data.
     *
     * @return ArrayList of String each containing the data representation of a task in the list.
     */
    public ArrayList<String> convertToDataList() {
        ArrayList<String> dataList = new ArrayList<>();
        for (Task task : tasks) {
            dataList.add(task.toData());
        }
        return dataList;
    }

    public void findAndDisplay(String input) {
        int index = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                System.out.println("\t" + Integer.valueOf(++index).toString() + ". " + task);
            }
        }
    }

}

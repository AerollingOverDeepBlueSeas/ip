package tete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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
    public String addItem(Task newTask) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.toString().equals(newTask.toString())) {
                found = true;
            }
        }
        if (found) {
            return UI.returnUnsuccessfulAddMessage(newTask);
        }
        tasks.add(newTask);
        UI.displaySuccessfulAddMessage(newTask, tasks);
        return UI.returnSuccessfulAddMessage(newTask, tasks);
    }

    /**
     * Removes a task from the list, given its position in the list.
     *
     * @param input String representation of the position of the task.
     * @throws InvalidIndexException when the input leads to an invalid index.
     */
    public String removeItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            System.out.println(tasks.size());
            UI.displaySuccessfulDeleteMessage(tasks, tasks.remove(index));
            return UI.returnSuccessfulDeleteMessage(tasks, tasks.remove(index));
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    /** Prints all tasks to screen. */
    public String displayItems() {
        int index = 0;
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            String line = Integer.valueOf(++index).toString() + ". " + task.toString();
            UI.displaySomeLine(line);
            output.append(line).append("\n");
        }
        return output.toString();
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
                // tete.Todo
                    new Todo(components[2], components[1].equals("X"));
            case "D" ->
                // tete.Deadline
                    new Deadline(components[2], LocalDate.parse(components[3]), components[1].equals("X"));
            case "E" ->
                // tete.Event
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
    public String markItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).markAsDone();
            return UI.returnSuccessfulMarkMessage(tasks.get(index));
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
    public String unmarkItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).unmarkAsDone();
            return UI.returnSuccessfulUnmarkMessage(tasks.get(index));
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
        tasks.forEach(task -> dataList.add(task.toData()));
        return dataList;
    }

    public String findAndDisplay(String input) {
        AtomicInteger index = new AtomicInteger();
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                String line = "\t" + Integer.valueOf(index.incrementAndGet()).toString() + ". " + task;
                System.out.println(line);
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }

}

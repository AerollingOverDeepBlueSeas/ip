package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addItem(Task newTask) {
        tasks.add(newTask);
        System.out.println("\tIn accordance to your wishes, the following task has been added: ");
        System.out.println("\t  " + newTask);
        System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
    }

    public void removeItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            System.out.println("In accordance to your wishes, the following task has been removed: ");
            System.out.println("\t  " + tasks.get(index));
            tasks.remove(index);
            System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    public void displayItems() {
        int index = 0;
        for (Task task : tasks) {
            System.out.println("\t" + Integer.valueOf(++index).toString() + ". " + task.toString());
        }
    }

    public void addItemFromFile(String line) {
        String[] components = line.split(" \\| ");
        Task newTask = new Task("default");

        if (components[0].equals("T")) {
            // duke.Todo
            newTask = new Todo(components[2], components[1].equals("X"));
        } else if (components[0].equals("D")) {
            // duke.Deadline
            newTask = new Deadline(components[2], LocalDate.parse(components[3]), components[1].equals("X"));
        } else if (components[0].equals("E")) {
            // duke.Event
            newTask = new Event(components[2], LocalDate.parse(components[3]),
                    LocalDate.parse(components[4]), components[1].equals("X"));
        }

        tasks.add(newTask);

    }

    public void markItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).markAsDone();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    public void unmarkItem(String input) throws InvalidIndexException {
        try {
            int index = validateIndex(input);
            tasks.get(index).unmarkAsDone();
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException();
        }
    }

    public int validateIndex(String input) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new InvalidIndexException();
            }
            return index;
        } catch (Exception e) {
            // Any exceptions caught here is caused by an invalid index
            throw new InvalidIndexException();
        }

    }

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
            if (task.getDescription().equals(input)) {
                System.out.println("\t" + Integer.valueOf(++index).toString() + ". " + task.toString());
            }
        }
    }

}

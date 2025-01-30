import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

enum Command {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    BYE
}

public class Tete {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addItem(Task newTask) {
        tasks.add(newTask);
        System.out.println("\tIn accordance to your wishes, the following task has been added: ");
        System.out.println("\t  " + newTask);
        System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
    }

    public static void addItemFromFile(String line) {
        String[] components = line.split(new String(" | "));
        Task newTask = new Task("default");

        if (components[0].equals("T")) {
            // Todo
            newTask = new Todo(components[1]);
        } else if (components[0].equals("D")) {
            // Deadline
            newTask = new Deadline(components[1], components[2]);
        } else if (components[0].equals("E")) {
            // Event
            newTask = new Event(components[0], components[1], components[2]);
        }

        tasks.add(newTask);

    }

    public static void removeItem(int index) {
        System.out.println("In accordance to your wishes, the following task has been removed: ");
        System.out.println("\t  " + tasks.get(index));
        tasks.remove(index);
        System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
    }

    public static void displayItems() {
        int index = 0;
        for (Task task : tasks) {
            System.out.println("\t" + Integer.valueOf(++index).toString() + ". " + task.toString());
        }
    }

    public static void main(String[] args) {

        String line = "\t⊱ ──── {⋆⌘⋆} ──── ⊰⊱ ────── {⋆⌘⋆} ────── ⊰⊱ ──── {⋆⌘⋆} ──── ⊰";
        String greeting = """
                    Greetings, I'm Tete.
                    How may I be of service to you?
                """;
        String farewell = "\tFarewell. May our paths cross again soon." +
                "\n\t...or not.";

        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        pw.println(line);
        pw.println(greeting);
        pw.println(line);
        String input = "";
        String[] inputs;

        File data;
        FileWriter fw;

        // Attempting to create a file to store the information
        try {
            data = new File("./src/main/data/list.txt");
            if (data.createNewFile()) {
                pw.println("New file initialised.");
            } else {
                pw.println("There appears to be existing data from the record. They shall be added to the list.");
            }
            fw = new FileWriter(data);
        } catch (IOException e) {
            pw.println(e.getMessage());
        } finally {
            pw.flush();
        }

        while (!input.equalsIgnoreCase("bye")) {
            try {
                input = sc.nextLine();
                inputs = input.split(" ");
                Command command;
                try {
                    command = Command.valueOf(inputs[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidCommandException();
                }
                int index;
                switch (command) {
                    case LIST:
                        displayItems();
                        break;
                    case MARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).markAsDone();
                        } else {
                            throw new InvalidIndexException();
                        }
                        break;
                    case UNMARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).unmarkAsDone();
                        } else {
                            throw new InvalidIndexException();
                        }
                        break;
                    case DELETE:
                        index = Integer.parseInt(inputs[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            removeItem(index);
                        } else {
                            throw new InvalidIndexException();
                        }
                        break;
                    case TODO:
                        if (inputs.length > 1) {
                            addItem(new Todo(input.replaceFirst("todo ", "")));
                        } else {
                            throw new EmptyTodoException();
                        }
                        break;
                    case DEADLINE:
                        if (inputs.length > 1) {
                            if (input.contains(" /by ")) {
                                String[] temp = input.replaceFirst("deadline ", "").split(" /by ");
                                addItem(new Deadline(temp[0], temp[1]));
                            } else {
                                throw new MissingFieldException("/by");
                            }
                        } else {
                            throw new EmptyDeadlineException();
                        }
                        break;
                    case EVENT:
                        if (inputs.length > 1) {
                            if (input.contains(" /from ") && input.contains(" /to ")) {
                                String[] temp = input.replaceFirst("event ", "")
                                        .replaceFirst(" /from ", "---")
                                        .replaceFirst(" /to ", "---")
                                        .split("---");
                                if (temp.length == 3) {
                                    addItem(new Event(temp[0], temp[1], temp[2]));
                                } else {
                                    throw new MissingFieldContentsException("/from and/or /to");
                                }
                            } else {
                                throw new MissingFieldException(" /from and/or /to");
                            }
                        } else {
                            throw new EmptyEventException();
                        }
                    case BYE:
                        break;
                }
            } catch (TeteException e) {
                pw.println(e.getMessage());
            } finally {
                pw.println();
                pw.println(line);
                pw.flush();
            }
        }

        pw.println(farewell);
        pw.println(line);
        pw.flush();
    }
}

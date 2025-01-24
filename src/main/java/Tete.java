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
    BYE;
}

public class Tete {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addItem(Task newTask) {
        tasks.add(newTask);
        System.out.println("\tIn accordance to your wishes, the following task has been added: ");
        System.out.println("\t  " + newTask);
        System.out.println("\tYou now have " + tasks.size() + ((tasks.size()==1)?" task.":" tasks."));
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
        String farewell = "\tFarewell. May our paths cross again soon. \n";

        Scanner sc = new Scanner(System.in);

        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
        String input = "";
        String[] inputs;

        while (!input.equalsIgnoreCase("bye")) {
            try {
                input = sc.nextLine();
                inputs = input.split(" ");
                Command command = null;
                try {
                    command = Command.valueOf(input.toUpperCase());
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
                }
            } catch (TeteException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
                System.out.println(line);
            }
        }

        System.out.println(farewell);
        System.out.println(line);
    }
}

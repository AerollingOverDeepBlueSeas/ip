package tete;

import java.io.IOException;
import java.time.LocalDate;

/** Represents the process that parses and executes the commands input by the user. */
public class Parser {

    private enum Command {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        BYE
    }

    /**
     * Validates the date given in yyyy-mm-dd format and returns it as a LocalDate if date is valid.
     *
     * @param input String of (possibly) a date in format yyyy-mm-dd.
     * @return LocalDate of given date if input is valid.
     * @throws InvalidDateException if input is not a date, not a valid date, or in the wrong format.
     */
    public static LocalDate validateDate(String input) throws InvalidDateException {
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

    /** Creates a new Parser object.
     * As no attributes are needed by Parser, this function is empty.
     */
    public Parser() {

    }

    /**
     * Processes, validates and executes commands.
     *
     * @param input from user, which may contain a valid command.
     * @param tasks for commands involving the current list of tasks.
     * @param storage for commands involving file operations.
     * @throws TeteException if command is invalid or other exceptions are encountered when executing a valid command.
     * @return String containing output of command execution, or error message
     */
    String parseCommand(String input, TaskList tasks, Storage storage) throws TeteException {
        Command command;
        String[] inputs = input.split(" ");
        String output = "";

        // Validating command keyword
        try {
            command = Command.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch (command) {
        case LIST:
            output = tasks.displayItems();
            break;
        case MARK:
            output = tasks.markItem(inputs[1]);
            break;
        case UNMARK:
            output = tasks.unmarkItem(inputs[1]);
            break;
        case DELETE:
            validateDeleteCommand(inputs);
            output = tasks.removeItem(inputs[1]);
            break;
        case TODO:
            validateTodoCommand(input, inputs);
            output = executeTodoCommand(input, tasks);
            break;
        case DEADLINE:
            validateDeadlineCommand(input, inputs);
            output = executeDeadlineCommand(input, tasks);
            break;
        case EVENT:
            String[] temp = validateEventCommand(input, inputs);
            output = executeEventCommand(temp, tasks);
            break;
        case FIND:
            output = tasks.findAndDisplay(inputs[1]);
            break;
        case BYE:
            executeByeCommand(tasks, storage);
            output = "bye";
            break;
        }

        return output;

    }

    private static void validateDeleteCommand(String[] inputs) throws EmptyDeleteException {
        if (inputs.length != 2) {
            throw new EmptyDeleteException();
        }
    }

    private static void validateTodoCommand(String input, String[] inputs) {
        if (inputs.length <= 1) {
            throw new EmptyTodoException();
        }
    }

    private static String executeTodoCommand(String input, TaskList tasks) {
        return tasks.addItem(new Todo(input.replaceFirst("todo ", "")));
    }

    private static void validateDeadlineCommand(String input, String[] inputs) throws TeteException {
        if (inputs.length == 0 ) {
            throw new EmptyDeadlineException();
        } else if (!input.contains(" /by ")) {
            if (input.contains("/by")) {
                throw new MissingFieldContentsException("/by");
            } else {
                throw new MissingFieldException("/by");
            }
        }
    }

    private static String executeDeadlineCommand(String input, TaskList tasks) throws TeteException {
        String[] temp = input.replaceFirst("deadline ", "").split(" /by ");
        LocalDate by = Parser.validateDate(temp[1]);
        return tasks.addItem(new Deadline(temp[0], by));
    }

    private static String[] validateEventCommand(String input, String[] inputs) throws TeteException {
        if (inputs.length > 1) {
            if (input.contains(" /from ") && input.contains(" /to ")) {
                String[] temp = input.replaceFirst("event ", "")
                        .replaceFirst(" /from ", "---")
                        .replaceFirst(" /to ", "---")
                        .split("---");
                if (temp.length == 3) {
                    return temp;
                } else {
                    throw new MissingFieldContentsException("/from and/or /to");
                }
            } else {
                if (input.contains(" /to")) {
                    throw new MissingFieldContentsException("/to");
                } else {
                    throw new MissingFieldException("/from and/or /to");
                }
            }
        } else {
            throw new EmptyEventException();
        }
    }

    private static String executeEventCommand(String[] inputs, TaskList tasks) throws TeteException {
        LocalDate from = Parser.validateDate(inputs[1]);
        LocalDate to = Parser.validateDate(inputs[2]);
        return tasks.addItem(new Event(inputs[0], from, to));
    }

    private static void executeByeCommand(TaskList tasks, Storage storage) throws TeteException {
        try {
            storage.saveAndClose(tasks.convertToDataList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

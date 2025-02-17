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
            if (inputs.length == 2) {
                output = tasks.removeItem(inputs[1]);
            } else {
                throw new EmptyDeleteException();
            }
            break;
        case TODO:
            if (inputs.length > 1) {
                output = tasks.addItem(new Todo(input.replaceFirst("todo ", "")));
            } else {
                throw new EmptyTodoException();
            }
            break;
        case DEADLINE:
            if (inputs.length > 1) {
                if (input.contains(" /by ")) {
                    String[] temp = input.replaceFirst("deadline ", "").split(" /by ");
                    LocalDate by = Parser.validateDate(temp[1]);
                    output = tasks.addItem(new Deadline(temp[0], by));
                } else {
                    if (input.contains("/by")) {
                        throw new MissingFieldContentsException("/by");
                    } else {
                        throw new MissingFieldException("/by");
                    }
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
                        LocalDate from = Parser.validateDate(temp[1]);
                        LocalDate to = Parser.validateDate(temp[2]);
                        output = tasks.addItem(new Event(temp[0], from, to));
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
        case FIND:
            output = tasks.findAndDisplay(inputs[1]);
            break;
        case BYE:
            try {
                storage.saveAndClose(tasks.convertToDataList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }

        return output;

    }


}

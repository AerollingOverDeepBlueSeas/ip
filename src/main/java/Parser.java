import java.io.IOException;
import java.time.LocalDate;

public class Parser {

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

    public static LocalDate validateDate(String input) throws InvalidDateException {
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

    public Parser() {

    }

    public void parseCommand(String input, TaskList tasks, Storage storage) throws TeteException {
        Command command;
        String[] inputs = input.split(" ");

        // Validating command keyword
        try {
            command = Command.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch (command) {
            case LIST:
                tasks.displayItems();
                break;
            case MARK:
                tasks.markItem(inputs[1]);
                break;
            case UNMARK:
                tasks.unmarkItem(inputs[1]);
                break;
            case DELETE:
                tasks.removeItem(inputs[1]);
                break;
            case TODO:
                if (inputs.length > 1) {
                    tasks.addItem(new Todo(input.replaceFirst("todo ", "")));
                } else {
                    throw new EmptyTodoException();
                }
                break;
            case DEADLINE:
                if (inputs.length > 1) {
                    if (input.contains(" /by ")) {
                        String[] temp = input.replaceFirst("deadline ", "").split(" /by ");
                        LocalDate by = Parser.validateDate(temp[1]);
                        tasks.addItem(new Deadline(temp[0], by));
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
                            LocalDate from = Parser.validateDate(temp[1]);
                            LocalDate to = Parser.validateDate(temp[2]);
                            tasks.addItem(new Event(temp[0], from, to));
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
                try {
                    storage.saveAndClose(tasks.convertToDataList());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }


}

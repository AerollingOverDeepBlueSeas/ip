package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static final TaskList tasklist = new TaskList();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();
    private static final ArrayList<Task> t = new ArrayList<>();
    private static final ArrayList<String> tasks = new ArrayList<>();

    @Test
    public void addValidTodoTest() {
        String command = "todo Fight worms";
        parser.parseCommand(command, tasklist, storage);
        Todo newItem = new Todo("Fight worms");
        t.add(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void addValidDeadlineTest() {
        String command = "deadline finish typing /by 2025-02-05";
        parser.parseCommand(command, tasklist, storage);
        Deadline newItem = new Deadline("finish typing", LocalDate.parse("2025-02-05"));
        t.add(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void addValidEventTest() {
        String command = "event attend meeting /from 2025-02-05 /to 2025-02-05";
        parser.parseCommand(command, tasklist, storage);
        Event newItem = new Event("attend meeting", LocalDate.parse("2025-02-05"),
                LocalDate.parse("2025-02-05"));
        t.add(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void addEmptyTodo_emptyTodoException_exceptionThrownTest() {
        try {
            String command = "todo";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tSurely there must be something you ought to do?\
                
                \tOr did you stumble upon this command on by accident?\
                
                \t(Enter some text after the 'todo' command.)""", e.getMessage());
        }
    }

    @Test
    public void addEmptyDeadline_emptyDeadlineException_exceptionThrownTest() {
        try {
            String command = "deadline";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tSurely there was a deadline you wanted to meet?\
                
                \t...or did you simply wish to relish in the lack of actual deadlines?\
                
                \tIf so, I recommend actually creating a deadline and striking it out.\
                
                \tIt tends to be more satisfying that way.\
                
                \t(Enter some text after the 'deadline' command)""", e.getMessage());
        }
    }

    @Test
    public void addEmptyEvent_emptyEventException_exceptionThrownTest() {
        try {
            String command = "event";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tSurely there was an event you need to attend?\
                
                \t...or did you simply wish to be invited to one?\
                
                \t(Enter some text after the 'event' command.)""", e.getMessage());
        }
    }

    @Test
    public void addIncompleteDeadline_missingFieldException_exceptionThrownTest() {
        try {
            String command = "deadline find me";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("\tYour command seems to be missing the component /by." +
                    "\n\tPlease take note to include what is needed for each command." +
                    "\n\tAfter all, what use is a reminder that cannot remind you of the most important information?",
                    e.getMessage());
        }
    }

    @Test
    public void addIncompleteEvent_missingFieldException_exceptionThrownTest() {
        try {
            String command = "event finish this";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("\tYour command seems to be missing the component /from and/or /to." +
                    "\n\tPlease take note to include what is needed for each command." +
                    "\n\tAfter all, what use is a reminder that cannot remind you of the most important information?",
                    e.getMessage());
        }
    }

    @Test
    public void addIncompleteDeadline_missingFieldContentsException_exceptionThrownTest() {
        try {
            String command = "deadline find me /by";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("\tYour command seems to be missing information for the following field(s): /by.",
                    e.getMessage());
        }
    }

    @Test
    public void addIncompleteEvent_f_missingFieldContentsException_exceptionThrownTest() {
        try {
            String command = "event finish this /from /to 2025-02-05";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals(
                    "\tYour command seems to be missing information for the following field(s): /from and/or /to.",
                    e.getMessage());
        }
    }

    @Test
    public void addIncompleteEvent_t_missingFieldContentsException_exceptionThrownTest() {
        try {
            String command = "event finish this /from 2025-02-05 /to";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals(
                    "\tYour command seems to be missing information for the following field(s): /to.",
                    e.getMessage());
        }
    }

    @Test
    public void markValidItemTest() {
        String command = "mark 1";
        parser.parseCommand(command, tasklist, storage);
        t.get(0).markAsDone();
        tasks.set(0, t.get(0).toData());
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void markInvalidItemTest() {
        try {
            String command = "mark 10";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""", e.getMessage());
        }
    }

    @Test
    public void unmarkValidItemTest() {
        String command = "unmark 1";
        parser.parseCommand(command, tasklist, storage);
        t.get(0).unmarkAsDone();
        tasks.set(0, t.get(0).toData());
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void unmarkInvalidItemTest() {
        try {
            String command = "unmark 10";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""", e.getMessage());
        }
    }

    @Test
    public void deleteValidItemTest() {
        String command = "delete 1";
        parser.parseCommand(command, tasklist, storage);
        t.remove(0);
        tasks.remove(0);
        assertEquals(tasks, tasklist.convertToDataList());
    }

    @Test
    public void deleteInvalidItemTest() {
        try {
            String command = "delete 10";
            parser.parseCommand(command, tasklist, storage);
        } catch (TeteException e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""", e.getMessage());
        }
    }

}

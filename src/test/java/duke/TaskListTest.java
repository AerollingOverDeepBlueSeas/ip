package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private static final TaskList taskList = new TaskList();
    private static final ArrayList<String> tasks = new ArrayList<>();

    @Test
    public void addTodoTest() {
        Todo newItem = new Todo("Feed worms");
        taskList.addItem(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, taskList.convertToDataList());
    }

    @Test
    public void addDeadlineTest() {
        Deadline newItem = new Deadline("Complete a thing", LocalDate.parse("2025-12-25"));
        taskList.addItem(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, taskList.convertToDataList());
    }

    @Test
    public void addEventTest() {
        Event newItem = new Event("Attend an event", LocalDate.parse("2025-12-25"),
                LocalDate.parse("2025-12-26"));
        taskList.addItem(newItem);
        tasks.add(newItem.toData());
        assertEquals(tasks, taskList.convertToDataList());
    }

    @Test
    public void markItem_invalidIndexException_exceptionThrown() {
        try {
            taskList.markItem("5");
        } catch (Exception e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""",
                    e.getMessage());
        }
    }

    @Test
    public void unmarkItem_invalidIndexException_exceptionThrown() {
        try {
            taskList.unmarkItem("5");
        } catch (Exception e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""",
                    e.getMessage());
        }
    }

    @Test
    public void removeItem_invalidIndexException_exceptionThrown() {
        try {
            taskList.removeItem("5");
        } catch (Exception e) {
            assertEquals("""
                \tThere is no task with the specified index.\
                
                \tYou may want to run the command 'list' again.\
                
                \tFortunately, one of us here has the items remembered.""",
                    e.getMessage());
        }
    }

    //TODO mark unmark and delete tests

}

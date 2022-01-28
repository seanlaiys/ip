package alfredtest;

import alfred.action.TaskList;
import alfred.task.Task;
import alfred.task.ToDo;
import alfred.task.Deadline;
import alfred.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTaskToTaskList() {
        Task todo = new ToDo("todo read book", true);
        TaskList test = new TaskList();
        test.addTasks(todo);
        assertEquals("1.[T][X] todo read book" + "\n", test.toString());
    }

    @Test
    public void testRemoveTaskFromTaskList() {
        Task todo = new ToDo("todo read book", true);
        Task todo1 = new ToDo("todo read dictionary", true);
        TaskList test = new TaskList();
        test.addTasks(todo);
        test.addTasks(todo1);
        test.removeTasks(1);
        assertEquals("1.[T][X] todo read book" + "\n", test.toString());
    }

}

package alfredtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import alfred.action.TaskList;
import alfred.task.Task;
import alfred.task.ToDo;

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

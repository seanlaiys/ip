package alfredtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.ToDo;

public class TaskTest {

    @Test
    public void testToDoToString() {
        assertEquals("[T][X] todo read book",
                new ToDo("todo read book", true, 0).toString());
    }

    @Test
    public void testDeadlineToString() {
        assertEquals("[D][X] deadline return book (by: Dec 2 2019 06:00 PM)",
                new Deadline("deadline return book ", LocalDate.parse("2019-12-02"),
                        LocalTime.parse("18:00"), true, 0).toString());
    }

    @Test
    public void testEventToString() {
        assertEquals("[E][ ] event throw party (at: Dec 12 2022 02:00 PM)",
                new Event("event throw party ", LocalDate.parse("2022-12-12"),
                        LocalTime.parse("14:00")).toString());
    }
}

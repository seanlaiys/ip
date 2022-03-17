package alfred.action;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import alfred.exception.StorageFailureException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.ToDo;
/**
 * Stores the current tasks in an ArrayList.
 */
public class Storage {

    private static final String TODO_KEY = "[T]";
    private static final String DEADLINE_KEY = "[D]";
    private static final String EVENT_KEY = "[E]";
    private File taskFile;
    private boolean isNew;

    /**
     * Creates a new Storage object.
     */
    public Storage() {
        this.isNew = true;
        this.taskFile = new File("taskFile.txt");
        this.taskFile = new File(this.taskFile.getAbsolutePath());
        try {
            this.isNew = this.taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns whether the file is new or old.
     *
     * @return a boolean of the file is new
     */
    public boolean isNewFile() {
        return isNew;
    }

    /**
     * Adds strings to a file.
     *
     * @param filePath the String representing the filepath to the file to be appended to
     * @param textToAppend the String to be appended to file
     * @throws IOException if an input or output exception occurred
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes strings to a file.
     *
     * @param filePath the String representing the filepath to the file to be written to
     * @param textToAdd the String to be added to file
     * @throws IOException if an input or output exception occurred
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes a list of tasks to TaskFile.
     *
     * @param tasks the list of tasks to be written to TaskFile
     * @throws IOException if an input or output exception occurred
     */
    public void writeTasksToFile(TaskList tasks) throws IOException {
        String fileContent = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            fileContent += tasks.getTask(i).toString() + System.lineSeparator();
        }
        try {
            assert taskFile != null : "taskFile cannot be null";
            writeToFile(taskFile.getAbsolutePath(), fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a task to TaskFile.
     *
     * @param task the task to be added to TaskFile
     * @throws IOException if an input or output exception occurred
     */
    public void appendTaskToFile(Task task) throws IOException {
        try {
            assert taskFile != null : "taskFile cannot be null";
            appendToFile(taskFile.getAbsolutePath(), task.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a TaskList which contains contents in an existing TaskFile.
     *
     * @throws FileNotFoundException if a TaskFile could not be found
     */
    public TaskList fileToTaskList() throws FileNotFoundException, StorageFailureException {
        assert taskFile != null : "taskFile cannot be null";
        Scanner s = new Scanner(taskFile); // create a Scanner using the File as the source
        String current;
        TaskList tasks = new TaskList();
        while (s.hasNext()) {
            current = s.nextLine();
            String taskWord = current.substring(0, 3);
            switch (taskWord) {
            case TODO_KEY:
                Task newToDo = handleToDo(current);
                tasks.addTasks(newToDo);
                break;
            case EVENT_KEY:
                Task newEvent = handleEvent(current);
                tasks.addTasks(newEvent);
                break;
            case DEADLINE_KEY:
                Task newDeadline = handleDeadline(current);
                tasks.addTasks(newDeadline);
                break;
            default:
                throw new StorageFailureException();
            }
        }
        return tasks;
    }

    /**
     * Returns a ToDo task from file storage.
     *
     * @param input the input line from storage
     * @return a Task based on the arguments passed in
     */
    private Task handleToDo(String input) {
        String description = input.replace(TODO_KEY, "");
        boolean done = false;
        if (description.contains(Task.DONE)) {
            done = true;
            description = description.replace(Task.DONE + " ", "");
        }
        description = description.replace(Task.NOT_DONE + " ", "");
        int priority = extractPriority(description);
        description = description.replace(Task.intToPriority(priority), "");
        return new ToDo(description, done, priority);
    }

    /**
     * Returns an Event task from file storage.
     *
     * @param input the input line from storage
     * @return a Task based on the arguments passed in
     */
    private Task handleEvent(String input) {
        String event = input.replace(EVENT_KEY, "");
        boolean done = false;
        if (event.contains(Task.DONE)) {
            done = true;
            event = event.replace(Task.DONE + " ", "");
        }
        event = event.replace(Task.NOT_DONE + " ", "");
        int priority = extractPriority(event);
        event = event.replace(Task.intToPriority(priority), "");
        String description = extractEvent(event);
        LocalDate date = extractEventDate(event);
        LocalTime time = extractEventTime(event);
        return new Event(description, date, time, done, priority);
    }

    /**
     * Returns the event description from file storage.
     *
     * @param input the input line from storage
     * @return a description String based on the input
     */
    private String extractEvent(String input) {
        String[] inputs = input.split(" \\(at: ");
        return inputs[0];
    }

    /**
     * Returns the event date from file storage.
     *
     * @param input the input line from storage
     * @return a LocalDate based on the input
     */
    private LocalDate extractEventDate(String input) {
        String[] inputs = input.split(" \\(at: ");
        String[] dateTime = inputs[1].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM-d-yyyy");
        String date = dateTime[0] + "-" + dateTime[1] + "-" + dateTime[2];
        return LocalDate.parse(date, formatterDate);
    }

    /**
     * Returns the event time from file storage.
     *
     * @param input the input line from storage
     * @return a LocalTime based on the input
     */
    private LocalTime extractEventTime(String input) {
        String[] inputs = input.split(" \\(at: ");
        String[] dateTime = inputs[1].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("hh:mma");
        String time = dateTime[3] + dateTime[4].substring(0, 2);
        return LocalTime.parse(time, formatterDate);
    }

    /**
     * Returns a Deadline Task from file storage.
     *
     * @param input the input line from storage
     * @return a Task based on the arguments passed in
     */
    private Task handleDeadline(String input) {
        String deadline = input.replace(DEADLINE_KEY, "");
        boolean done = false;
        if (deadline.contains(Task.DONE)) {
            done = true;
            deadline = deadline.replace(Task.DONE + " ", "");
        }
        deadline = deadline.replace(Task.NOT_DONE + " ", "");
        int priority = extractPriority(deadline);
        deadline = deadline.replace(Task.intToPriority(priority), "");
        String description = extractDeadline(deadline);
        LocalDate date = extractDeadlineDate(deadline);
        LocalTime time = extractDeadlineTime(deadline);
        return new Deadline(description, date, time, done, priority);
    }

    /**
     * Returns the deadline description from file storage.
     *
     * @param input the input line from storage
     * @return a description String based on the input
     */
    private String extractDeadline(String input) {
        String[] inputs = input.split(" \\(by: ");
        return inputs[0];
    }

    /**
     * Returns the deadline date from file storage.
     *
     * @param input the input line from storage
     * @return a LocalDate based on the input
     */
    private LocalDate extractDeadlineDate(String input) {
        String[] inputs = input.split(" \\(by: ");
        String[] dateTime = inputs[1].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM-d-yyyy");
        String date = dateTime[0] + "-" + dateTime[1] + "-" + dateTime[2];
        return LocalDate.parse(date, formatterDate);
    }

    /**
     * Returns the deadline time from file storage.
     *
     * @param input the input line from storage
     * @return a LocalTime based on the input
     */
    private LocalTime extractDeadlineTime(String input) {
        String[] inputs = input.split(" \\(by: ");
        String[] dateTime = inputs[1].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("hh:mma");
        String time = dateTime[3] + dateTime[4].substring(0, 2);
        return LocalTime.parse(time, formatterDate);
    }

    /**
     * Returns int representing priority from string input.
     *
     * @param input the input to extract priority from
     * @return int representing priority
     */
    private int extractPriority(String input) {
        if (input.contains(Task.HIGH_PRIORITY)) {
            return 3;
        } else if (input.contains(Task.MEDIUM_PRIORITY)) {
            return 2;
        } else if (input.contains(Task.LOW_PRIORITY)) {
            return 1;
        } else {
            return 0;
        }
    }

}

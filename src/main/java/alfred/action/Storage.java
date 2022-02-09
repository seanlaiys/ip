package alfred.action;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.ToDo;
/**
 * Stores the current tasks in an ArrayList.
 */
public class Storage {
    private File taskFile;
    private boolean isNew;

    /**
     * Creates a new Storage object.
     */
    public Storage() {
        this.isNew = true;
        this.taskFile = new File( "taskFile.txt");
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
            assert taskFile != null: "taskFile cannot be null";
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
            assert taskFile != null: "taskFile cannot be null";
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
    public TaskList fileToTaskList() throws FileNotFoundException {
        assert taskFile != null: "taskFile cannot be null";
        Scanner s = new Scanner(taskFile); // create a Scanner using the File as the source
        String current;
        TaskList tasks = new TaskList();
        while (s.hasNext()) {
            current = s.nextLine();
            if (current.contains(Commands.COMMAND_TODO)) {
                String[] descriptions = current.split(Commands.COMMAND_TODO);
                Task newTask = new ToDo(Commands.COMMAND_TODO + descriptions[1],
                        current.contains("[X]"));
                tasks.addTasks(newTask);
            } else if (current.contains(Commands.COMMAND_EVENT)) {
                String[] descriptions = current.split(Commands.COMMAND_EVENT);
                String openBracket = "\\(";
                String closeBracket = "\\)";
                String[] newDescriptions = descriptions[1].split(openBracket);
                String date = newDescriptions[1].split(closeBracket)[0];
                String[] dateSplits = date.split("at: ")[1].split(" ");
                date = dateSplits[0] + " " + dateSplits[1] + " " + dateSplits[2];
                String time = dateSplits[3] + dateSplits[4];
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mma");
                LocalDate newDate = LocalDate.parse(date, formatterDate);
                LocalTime newTime = LocalTime.parse(time, formatterTime);
                Task newTask = new Event(Commands.COMMAND_EVENT + newDescriptions[0],
                        newDate, newTime, current.contains("[X]"));
                tasks.addTasks(newTask);
            } else {
                String[] descriptions = current.split(Commands.COMMAND_DEADLINE);
                String openBracket = "\\(";
                String closeBracket = "\\)";
                String[] newDescriptions = descriptions[1].split(openBracket);
                String date = newDescriptions[1].split(closeBracket)[0];
                String[] dateSplits = date.split("by: ")[1].split(" ");
                date = dateSplits[0] + " " + dateSplits[1] + " " + dateSplits[2];
                String time = dateSplits[3] + dateSplits[4];
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mma");
                LocalDate newDate = LocalDate.parse(date, formatterDate);
                LocalTime newTime = LocalTime.parse(time, formatterTime);
                Task newTask = new Deadline(Commands.COMMAND_DEADLINE + newDescriptions[0],
                        newDate, newTime, current.contains("[X]"));
                tasks.addTasks(newTask);
            }
        }
        return tasks;
    }
}

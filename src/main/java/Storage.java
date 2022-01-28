import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
/**
 *
 * Stores the current tasks in an <code>ArrayList</code>.
 *
 */
public class Storage {
    File taskFile;
    private boolean isNew;

    /**
     * Creates a new Storage object.
     */
    public Storage() {
        this.isNew = true;
        this.taskFile = new File("data/taskFile.txt");
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
        for(int i = 0; i < tasks.getSize(); i++) {
            fileContent += tasks.getTask(i).toString() + System.lineSeparator();
        }
        try {
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
        Scanner s = new Scanner(taskFile); // create a Scanner using the File as the source
        String current;
        TaskList tasks = new TaskList();
        while (s.hasNext()) {
            current = s.nextLine();
            if (current.contains(Commands.COMMAND_TODO)){
                String[] descriptions = current.split(Commands.COMMAND_TODO);
                Task newTask = new ToDo(Commands.COMMAND_TODO + descriptions[1]);
                tasks.addTasks(newTask);
            } else if (current.contains(Commands.COMMAND_EVENT)){
                String[] descriptions = current.split(Commands.COMMAND_EVENT);
                String OPEN_BRAC = "\\(";
                String CLOSE_BRAC = "\\)";
                String[] newDescriptions = descriptions[1].split(OPEN_BRAC);
                String date = newDescriptions[1].split(CLOSE_BRAC)[0];
                String[] dateSplits = date.split("at: ")[1].split(" ");
                date = dateSplits[0]+ " " + dateSplits[1] + " " + dateSplits[2];
                String time = dateSplits[3] + dateSplits[4];
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mma");
                LocalDate newDate = LocalDate.parse(date, formatterDate);
                LocalTime newTime = LocalTime.parse(time, formatterTime);
                Task newTask = new Event(Commands.COMMAND_EVENT + newDescriptions[0], newDate, newTime);
                tasks.addTasks(newTask);
            } else {
                String[] descriptions = current.split(Commands.COMMAND_DEADLINE);
                String OPEN_BRAC = "\\(";
                String CLOSE_BRAC = "\\)";
                String[] newDescriptions = descriptions[1].split(OPEN_BRAC);
                String date = newDescriptions[1].split(CLOSE_BRAC)[0];
                String[] dateSplits = date.split("by: ")[1].split(" ");
                date = dateSplits[0]+ " " + dateSplits[1] + " " + dateSplits[2];
                String time = dateSplits[3] + dateSplits[4];
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mma");
                LocalDate newDate = LocalDate.parse(date, formatterDate);
                LocalTime newTime = LocalTime.parse(time, formatterTime);
                Task newTask = new Event(Commands.COMMAND_DEADLINE + newDescriptions[0], newDate, newTime);
                tasks.addTasks(newTask);
            }
        }
        return tasks;
    }
}

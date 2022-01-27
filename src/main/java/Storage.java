import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Stores the current tasks in an ArrayList.
 *
 */
public class Storage {
    File taskFile;
    private boolean isNew;

    public Storage() {
        this.isNew = true;
        this.taskFile = new File("data/taskFile.txt");
        try {
            this.isNew = this.taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNewFile() {
        return isNew;
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

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

    public void appendTaskToFile(Task task) throws IOException {
        try {
            appendToFile(taskFile.getAbsolutePath(), task.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                Task newTask = new Event(Commands.COMMAND_EVENT + newDescriptions[0], date);
                tasks.addTasks(newTask);
            } else {
                String[] descriptions = current.split(Commands.COMMAND_DEADLINE);
                String OPEN_BRAC = "\\(";
                String CLOSE_BRAC = "\\)";
                String[] newDescriptions = descriptions[1].split(OPEN_BRAC);
                String date = newDescriptions[1].split(CLOSE_BRAC)[0];
                Task newTask = new Deadline(Commands.COMMAND_DEADLINE + newDescriptions[0], date);
                tasks.addTasks(newTask);
            }
        }
        return tasks;
    }
}

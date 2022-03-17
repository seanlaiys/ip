package alfred.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import alfred.exception.AlfredException;
import alfred.exception.EmptyInputException;
import alfred.exception.ExcessKeywordsException;
import alfred.exception.InvalidCommandException;
import alfred.exception.InvalidDateException;
import alfred.exception.InvalidDateTimeException;
import alfred.exception.InvalidInputException;
import alfred.exception.InvalidTimeException;
import alfred.exception.StorageFailureException;
import alfred.exception.TaskNotFoundException;
import alfred.exception.TasksOutOfBoundsException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.ToDo;
/**
 * Serves to take in the input keyed in by the user
 * and invokes methods in response to what the user has keyed in.
 */
public class Parser {

    private static TaskList currentTasks;
    private static Storage currentStorage;

    /**
     * Creates new Parser object.
     */
    public Parser() {
        currentTasks = new TaskList();
        currentStorage = new Storage();
    }

    /**
     * Takes in input from user which contains keywords to commands
     * to invoke different methods based on the command given.
     *
     * @param input the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    public static void parseCommand(String input, Ui userInterface) throws AlfredException {
        initialiseCurrentTasks();
        String[] inputs = input.split(" ");
        String commandWord = inputs[0];
        switch (commandWord) {
        case "":
            throw new EmptyInputException();
        case Commands.COMMAND_BYE:
            userInterface.sayGoodbye();
            break;
        case Commands.COMMAND_LIST:
            handleList(inputs, userInterface);
            break;
        case Commands.COMMAND_FIND:
            handleFind(inputs, userInterface);
            break;
        case Commands.COMMAND_BLAH:
            userInterface.sayBlah();
            break;
        case Commands.COMMAND_UNMARK:
            handleUnmark(inputs, userInterface);
            break;
        case Commands.COMMAND_MARK:
            handleMark(inputs, userInterface);
            break;
        case Commands.COMMAND_PRIORITY:
            handlePriority(inputs, userInterface);
            break;
        case Commands.COMMAND_TODO:
            handleToDo(input, userInterface);
            break;
        case Commands.COMMAND_DEADLINE:
            handleDeadline(input, userInterface);
            break;
        case Commands.COMMAND_EVENT:
            handleEvent(input, userInterface);
            break;
        case Commands.COMMAND_DELETE:
            handleDelete(inputs, userInterface);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Initialises the current tasks based on storage.
     */
    private static void initialiseCurrentTasks() throws AlfredException {
        if (currentStorage.isNewFile()) {
            return;
        }
        try {
            currentTasks = currentStorage.fileToTaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new alfred.exception.FileNotFoundException();
        }
    }

    /**
     * Updates the list onto the user interface.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleList(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length == 1) {
            userInterface.generateList(currentTasks);
            return;
        }
        String keyword = inputs[1];
        switch (keyword) {
        case "":
            userInterface.generateList(currentTasks);
            break;
        case "/on":
            if (inputs.length != 3) {
                throw new InvalidDateException();
            }
            LocalDate date = getDate(inputs[2]);
            TaskList newTasks = currentTasks.getTasksByDate(date);
            userInterface.generateList(newTasks);
            break;
        default:
            throw new InvalidInputException();
        }
    }

    /**
     * Updates the list onto the user interface based on keyword.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleFind(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length == 1) {
            throw new InvalidCommandException();
        }
        if (inputs.length != 2) {
            throw new ExcessKeywordsException();
        }
        String keyword = inputs[1];
        if (keyword.equals(" ") || keyword.equals("")) {
            throw new EmptyInputException();
        }
        TaskList result = currentTasks.getTasksByKeyWord(keyword);
        if (result.getSize() == 0) {
            throw new TaskNotFoundException();
        }
        userInterface.generateList(result);
    }

    /**
     * Updates the list onto the user interface with a specified task unmarked.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleUnmark(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length == 1) {
            throw new InvalidCommandException();
        }
        if (inputs.length != 2) {
            throw new ExcessKeywordsException();
        }
        String keyword = inputs[1];
        int index = getIndex(keyword);
        if (currentTasks.getSize() <= index) {
            throw new TasksOutOfBoundsException();
        }
        currentTasks.unmarkTask(index);
        Task unmarked = currentTasks.getTask(index);
        userInterface.sayUnmark(unmarked);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a specified task marked.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleMark(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length == 1) {
            throw new InvalidCommandException();
        }
        if (inputs.length != 2) {
            throw new ExcessKeywordsException();
        }
        String keyword = inputs[1];
        int index = getIndex(keyword);
        if (currentTasks.getSize() <= index) {
            throw new TasksOutOfBoundsException();
        }
        currentTasks.markTask(index);
        Task marked = currentTasks.getTask(index);
        userInterface.sayMark(marked);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a new ToDo task.
     *
     * @param input the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleToDo(String input, Ui userInterface) throws AlfredException {
        String[] inputs = input.split(" ");
        String description = input.replace(Commands.COMMAND_TODO + " ", "");
        if (description.equals("") || inputs.length == 1) {
            throw new InvalidCommandException();
        }
        Task newTask = new ToDo(description);
        currentTasks.addTasks(newTask);
        userInterface.sayAdd(newTask, currentTasks);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a new Deadline task.
     *
     * @param input the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleDeadline(String input, Ui userInterface) throws AlfredException {
        String[] inputs = input.split(" ");
        String description = input.replace(Commands.COMMAND_DEADLINE + " ", "");
        if (description.equals("") || inputs.length == 1) {
            throw new InvalidCommandException();
        }
        String[] descriptions = description.split(" /by ");
        if (descriptions.length != 2) {
            throw new InvalidCommandException();
        }
        String[] dateTime = descriptions[1].split(" ");
        if (dateTime.length != 2) {
            throw new InvalidDateTimeException();
        }
        description = descriptions[0];
        LocalDate date = getDate(dateTime[0]);
        LocalTime time = getTime(dateTime[1]);
        Task newTask = new Deadline(description, date, time);
        currentTasks.addTasks(newTask);
        userInterface.sayAdd(newTask, currentTasks);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a new Event task.
     *
     * @param input the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleEvent(String input, Ui userInterface) throws AlfredException {
        String[] inputs = input.split(" ");
        String description = input.replace(Commands.COMMAND_EVENT + " ", "");
        if (description.equals("") || inputs.length == 1) {
            throw new InvalidCommandException();
        }
        String[] descriptions = description.split(" /at ");
        if (descriptions.length != 2) {
            throw new InvalidCommandException();
        }
        String[] dateTime = descriptions[1].split(" ");
        if (dateTime.length != 2) {
            throw new InvalidDateTimeException();
        }
        description = descriptions[0];
        LocalDate date = getDate(dateTime[0]);
        LocalTime time = getTime(dateTime[1]);
        Task newTask = new Event(description, date, time);
        currentTasks.addTasks(newTask);
        userInterface.sayAdd(newTask, currentTasks);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a specified task removed.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handleDelete(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length == 1) {
            throw new InvalidCommandException();
        }
        if (inputs.length != 2) {
            throw new ExcessKeywordsException();
        }
        String keyword = inputs[1];
        int index = getIndex(keyword);
        if (currentTasks.getSize() <= index) {
            throw new TasksOutOfBoundsException();
        }
        Task removedTask = currentTasks.getTask(index);
        currentTasks.removeTasks(index);
        userInterface.sayDelete(removedTask, currentTasks);
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the list onto the user interface with a
     * specified priority added to a specified task.
     *
     * @param inputs the command input by user
     * @param userInterface the user interface that prints the response based on input
     */
    private static void handlePriority(String[] inputs, Ui userInterface) throws AlfredException {
        if (inputs.length < 3) {
            throw new InvalidCommandException();
        }
        if (inputs.length != 3) {
            throw new ExcessKeywordsException();
        }
        String priority = inputs[1];
        int index = getIndex(inputs[2]);
        if (currentTasks.getSize() <= index) {
            throw new TasksOutOfBoundsException();
        }
        currentTasks = parsePriority(priority, index, currentTasks);
        userInterface.sayPriority(currentTasks.getTask(index));
        try {
            currentStorage.writeTasksToFile(currentTasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFailureException();
        }
    }

    /**
     * Updates the task list with a specified priority to add to a specified task.
     *
     * @param priority the priority input by user
     * @param index the index of the specified task
     * @param currentTasks the current task list
     */
    private static TaskList parsePriority(String priority, int index, TaskList currentTasks)
            throws AlfredException {
        switch (priority) {
        case Commands.PRIORITY_HIGH:
            currentTasks.setTaskPriority(index, 3);
            break;
        case Commands.PRIORITY_MEDIUM:
            currentTasks.setTaskPriority(index, 2);
            break;
        case Commands.PRIORITY_LOW:
            currentTasks.setTaskPriority(index, 1);
            break;
        case Commands.PRIORITY_REMOVE:
            currentTasks.setTaskPriority(index, 0);
            break;
        default:
            throw new InvalidCommandException();
        }
        return currentTasks;
    }

    /**
     * Returns the date based on the input typed in by user.
     *
     * @param input the String input of the LocalDate
     * @return a LocalDate object of the date input by user
     * @throws InvalidDateException if input is invalid
     */
    private static LocalDate getDate(String input) throws InvalidDateException {
        if (input == null || input.equals("")) {
            throw new InvalidDateException();
        }
        String[] dateSplits = input.split("/");
        if (dateSplits.length != 3) {
            throw new InvalidDateException();
        }
        String date = dateSplits[2] + "-" + dateSplits[1] + "-" + dateSplits[0];
        try {
            LocalDate result = LocalDate.parse(date);
            return result;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns the time based on the input typed in by user.
     *
     * @param input the String input of the LocalTime
     * @return a LocalTime object of the date input by user
     * @throws InvalidTimeException if input is invalid
     */
    private static LocalTime getTime(String input) throws InvalidTimeException {
        if (input == null || input.equals("")) {
            throw new InvalidTimeException();
        }
        String[] timeSplits = input.split("");
        if (timeSplits.length != 4) {
            throw new InvalidTimeException();
        }
        try {
            LocalTime result = LocalTime.parse(timeSplits[0] + timeSplits[1]
                    + ":" + timeSplits[2] + timeSplits[3]);
            return result;
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Returns the index based on the input typed in by user.
     *
     * @param input the String input of the LocalDate
     * @return the integer of the index of the task specified by user
     * @throws AlfredException if input is invalid
     */
    private static int getIndex(String input) throws AlfredException {
        int index = -1;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        if (index < 0) {
            throw new TasksOutOfBoundsException();
        }
        return index;
    }
}

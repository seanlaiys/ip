package alfred.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import alfred.exception.AlfredException;
import alfred.exception.EmptyInputException;
import alfred.exception.InvalidDateException;
import alfred.exception.InvalidInputException;
import alfred.exception.MissingDescriptionException;
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
     */
    public static void parseCommand(String input, Ui userInterface) throws AlfredException {
        if (!currentStorage.isNewFile()) {
            try {
                currentTasks = currentStorage.fileToTaskList();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (input.contains(Commands.COMMAND_LIST)) {
            if (input.contains("/on")) {
                String[] inputs = input.split("/on ");
                try {
                    LocalDate date = getDate(inputs[1]);
                    TaskList newTasks = currentTasks.getTasksByDate(date);
                    userInterface.generateList(newTasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                userInterface.generateList(currentTasks);
            }
        } else if (input.contains(Commands.COMMAND_FIND)) {
            String[] descriptions = input.split(Commands.COMMAND_FIND);
            if (descriptions.length == 0) {
                throw new EmptyInputException();
            }
            TaskList result = currentTasks.getTasksByKeyWord(descriptions[0]);
            userInterface.generateList(result);
        } else if (input.contains(Commands.COMMAND_BLAH)) {
            userInterface.sayBlah();
        } else if (input.contains(Commands.COMMAND_UNMARK)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.unmarkTask(taskNumber);
            Task unmarked = currentTasks.getTask(taskNumber);
            userInterface.sayUnmark(unmarked);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_MARK)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.markTask(taskNumber);
            Task marked = currentTasks.getTask(taskNumber);
            userInterface.sayMark(marked);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_HIGH_PRIORITY)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.setTaskPriority(taskNumber, 3);
            Task newTask = currentTasks.getTask(taskNumber);
            userInterface.sayPriority(newTask);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_MEDIUM_PRIORITY)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.setTaskPriority(taskNumber, 2);
            Task newTask = currentTasks.getTask(taskNumber);
            userInterface.sayPriority(newTask);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_LOW_PRIORITY)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.setTaskPriority(taskNumber, 1);
            Task newTask = currentTasks.getTask(taskNumber);
            userInterface.sayPriority(newTask);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_REMOVE_PRIORITY)) {
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            currentTasks.setTaskPriority(taskNumber, 0);
            Task newTask = currentTasks.getTask(taskNumber);
            userInterface.sayPriority(newTask);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_TODO)) {
            String[] descriptions = input.split(Commands.COMMAND_TODO);
            if (descriptions.length == 0) {
                throw new MissingDescriptionException();
            }
            Task newTask = new ToDo(input);
            currentTasks.addTasks(newTask);
            userInterface.sayAdd(newTask, currentTasks);
            try {
                currentStorage.appendTaskToFile(newTask);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_DEADLINE)) {
            String[] descriptions = input.split(Commands.COMMAND_DEADLINE);
            if (descriptions.length == 0) {
                throw new MissingDescriptionException();
            }
            String[] inputs = input.split("/by ");
            try {
                isValidTime(inputs[1]);
                isValidDate(inputs[1]);
                Task newTask = new Deadline(inputs[0], getDate(inputs[1]),
                        getTime(inputs[1]));
                currentTasks.addTasks(newTask);
                userInterface.sayAdd(newTask, currentTasks);
                currentStorage.appendTaskToFile(newTask);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_EVENT)) {
            String[] descriptions = input.split(Commands.COMMAND_EVENT);
            if (descriptions.length == 0) {
                throw new MissingDescriptionException();
            }
            String[] inputs = input.split("/at ");
            try {
                isValidTime(inputs[1]);
                isValidDate(inputs[1]);
                Task newTask = new Event(inputs[0], getDate(inputs[1]),
                        getTime(inputs[1]));
                currentTasks.addTasks(newTask);
                userInterface.sayAdd(newTask, currentTasks);
                currentStorage.appendTaskToFile(newTask);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.contains(Commands.COMMAND_DELETE)) {
            String[] descriptions = input.split(Commands.COMMAND_DELETE);
            if (descriptions.length == 0) {
                throw new EmptyInputException();
            }
            int taskNumber = findDigit(input);
            isValidIndex(taskNumber);
            Task removedTask = currentTasks.getTask(taskNumber);
            currentTasks.removeTasks(taskNumber);
            userInterface.sayDelete(removedTask, currentTasks);
            try {
                currentStorage.writeTasksToFile(currentTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (input.equals("")) {
            throw new EmptyInputException();
        } else if (input.equals(Commands.COMMAND_BYE)) {
            userInterface.sayGoodbye();
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns the index of the task in the current action.TaskList
     * based on the input provided by the user containing a digit.
     *
     * @return an int index of the task
     * @throws EmptyInputException if input does not contain an index
     */
    public static int findDigit(String input) throws EmptyInputException {
        char[] inputChars = input.toCharArray();
        String number = "";
        for (char ch : inputChars) {
            if (Character.isDigit(ch)) {
                number += ch;
            }
        }
        if (number.equals("")) {
            throw new EmptyInputException();
        }
        int digit = Integer.parseInt(number);
        return digit - 1;
    }

    /**
     * Returns the date based on the input typed in by user.
     *
     * @param input the String input of the LocalDate
     * @return a LocalDate object of the date input by user
     * @throws IOException if input is invalid
     */
    public static LocalDate getDate(String input) throws IOException {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy M d");
        return LocalDate.parse(yearSplits[0] + " " + dateSplits[1] + " "
                + dateSplits[0], formatterDate);
    }

    /**
     * Returns the time based on the input typed in by user.
     *
     * @param input the String input of the LocalDate
     * @return a LocalTime object of the date input by user
     * @throws IOException if input is invalid
     */
    public static LocalTime getTime(String input) throws IOException {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        String[] timeSplits = yearSplits[1].split("");
        return LocalTime.parse(timeSplits[0] + timeSplits[1] + ":"
                + timeSplits[2] + timeSplits[3]);
    }

    /**
     * Throws exception if a date input by user is invalid.
     *
     * @param input the String input of the LocalDate
     * @throws InvalidDateException if input date is invalid
     */
    public static void isValidDate(String input) throws InvalidDateException {
        String[] dateSplits = input.split("/");
        if (dateSplits.length < 3) {
            throw new InvalidDateException();
        }
        if (dateSplits[2].split(" ")[0].split("").length != 4) {
            throw new InvalidDateException();
        }
    }

    /**
     * Throws exception if a time input by user is invalid.
     *
     * @param input the String input of the LocalDate
     * @throws InvalidDateException if input date is invalid
     */
    public static void isValidTime(String input) throws InvalidDateException {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        String[] timeSplits = yearSplits[1].split("");
        if (timeSplits.length != 4) {
            throw new InvalidDateException();
        }
    }

    /**
     * Throws exception if an index input by user is invalid.
     *
     * @param input the int input of index
     * @throws TasksOutOfBoundsException if input date is invalid
     */
    public static void isValidIndex(int input) throws TasksOutOfBoundsException {
        if (currentTasks.getSize() <= input) {
            throw new TasksOutOfBoundsException();
        }
        if (input < 0) {
            throw new TasksOutOfBoundsException();
        }
    }


}

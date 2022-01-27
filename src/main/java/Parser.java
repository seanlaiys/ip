import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * Serves to take in the input keyed in by the user
 * and invokes methods in response to what the user has keyed in.
 *
 */
public class Parser {
    /**
     * Takes in input from user which contains keywords to commands
     * to invoke different methods based on the command given
     */
    public static void readCommand() {
        String input;
        TaskList currentTasks = new TaskList();
        Storage currentStorage = new Storage();
        if (!currentStorage.isNewFile()) {
            try {
                currentTasks = currentStorage.fileToTaskList();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Scanner user_input = new Scanner(System.in);
        input = user_input.nextLine();
        while(!input.equals(Commands.COMMAND_BYE)) {
            if(input.contains(Commands.COMMAND_LIST)) {
                if(input.contains("/on")) {
                    String[] inputs = input.split("/on ");
                        try {
                            LocalDate date = getDate(inputs[1]);
                            TaskList newTasks = currentTasks.getTasksByDate(date);
                            input = Messages.generateList(user_input, newTasks);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                } else {
                    input = Messages.generateList(user_input, currentTasks);
                }
            } else if (input.contains(Commands.COMMAND_BLAH)) {
                input = Messages.sayBlah(user_input);
            } else if (input.contains(Commands.COMMAND_UNMARK)){
                int taskNumber = findDigit(input);
                currentTasks.unmarkTask(taskNumber);
                Task unmarked = currentTasks.getTask(taskNumber);
                input = Messages.sayUnmark(user_input, unmarked);
                try {
                    currentStorage.writeTasksToFile(currentTasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.contains(Commands.COMMAND_MARK)){
                int taskNumber = findDigit(input);
                currentTasks.markTask(taskNumber);
                Task marked = currentTasks.getTask(taskNumber);
                input = Messages.sayMark(user_input, marked);
                try {
                    currentStorage.writeTasksToFile(currentTasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.contains(Commands.COMMAND_TODO)){
                String[] descriptions = input.split(Commands.COMMAND_TODO);
                if (descriptions.length == 0) {
                    Messages.sayNoDescription();
                    input = user_input.nextLine();
                } else {
                    Task newTask = new ToDo(input);
                    currentTasks.addTasks(newTask);
                    input = Messages.sayAdd(user_input, newTask, currentTasks);
                    try {
                        currentStorage.appendTaskToFile(newTask);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }  else if (input.contains(Commands.COMMAND_DEADLINE)){
                String[] descriptions = input.split(Commands.COMMAND_DEADLINE);
                if (descriptions.length == 0) {
                    Messages.sayNoDescription();
                    input = user_input.nextLine();
                } else {
                    String[] inputs = input.split("/by ");
                    if (!isValidTime(inputs[1]) || !isValidDate(inputs[1])) {
                        Messages.sayInvalidDate();
                        input = user_input.nextLine();
                    } else {
                        try {
                            Task newTask = new Deadline(inputs[0], getDate(inputs[1]), getTime(inputs[1]));
                            currentTasks.addTasks(newTask);
                            input = Messages.sayAdd(user_input, newTask, currentTasks);
                            currentStorage.appendTaskToFile(newTask);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (input.contains(Commands.COMMAND_EVENT)){
                String[] descriptions = input.split(Commands.COMMAND_EVENT);
                if (descriptions.length == 0) {
                    Messages.sayNoDescription();
                    input = user_input.nextLine();
                } else {
                    String[] inputs = input.split("/at ");
                    if (!isValidTime(inputs[1]) || !isValidDate(inputs[1])) {
                        Messages.sayInvalidDate();
                        input = user_input.nextLine();
                    } else {
                        try {
                            Task newTask = new Event(inputs[0], getDate(inputs[1]), getTime(inputs[1]));
                            currentTasks.addTasks(newTask);
                            input = Messages.sayAdd(user_input, newTask, currentTasks);
                            currentStorage.appendTaskToFile(newTask);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }  else if (input.contains(Commands.COMMAND_DELETE)){
                String[] descriptions = input.split(Commands.COMMAND_DELETE);
                if (descriptions.length == 0) {
                    Messages.sayEmpty();
                    input = user_input.nextLine();
                } else {
                    int taskNumber = findDigit(input);
                    Task removedTask = currentTasks.getTask(taskNumber);
                    currentTasks.removeTasks(taskNumber);
                    input = Messages.sayDelete(user_input, removedTask, currentTasks);
                    try {
                        currentStorage.writeTasksToFile(currentTasks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (input.equals("")) {
                Messages.sayEmpty();
                input = user_input.nextLine();
            } else {
                Messages.sayUnsure();
                input = user_input.nextLine();
            }
        }
        Messages.sayGoodbye();
    }

    /**
     * Returns the index of the task in the current TaskList
     * based on the input provided by the user containing a digit
     *
     * @return an int index of the task
     */
    public static int findDigit(String input) {
        char[] inputChars = input.toCharArray();
        String number = "";
        for(char ch : inputChars) {
            if(Character.isDigit(ch)){
                number += ch;
            }
        }
        int digit = Integer.parseInt(number);
        return digit - 1;
    }

    /**
     * Returns the index of the task in the current TaskList
     * based on the input provided by the user containing a digit
     *
     * @param input the String input of the LocalDate
     * @return an int index of the task
     * @throws IOException if input is invalid
     */
    public static LocalDate getDate(String input) throws IOException {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy M d");
        return LocalDate.parse(yearSplits[0] + " " + dateSplits[1] + " " + dateSplits[0], formatterDate);
    }

    /**
     * Returns the index of the task in the current TaskList
     * based on the input provided by the user containing a digit
     *
     * @param input the String input of the LocalDate
     * @return an int index of the task
     * @throws IOException if input is invalid
     */
    public static LocalTime getTime(String input) throws IOException {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        String[] timeSplits = yearSplits[1].split("");
        return LocalTime.parse(timeSplits[0] + timeSplits[1] + ":" + timeSplits[2] + timeSplits[3]);
    }

    public static Boolean isValidDate(String input) {
        String[] dateSplits = input.split("/");
        if (dateSplits.length < 3) {
            return false;
        } else if (dateSplits[2].split(" ")[0].split("").length != 4) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isValidTime(String input) {
        String[] dateSplits = input.split("/");
        String[] yearSplits = dateSplits[2].split(" ");
        String[] timeSplits = yearSplits[1].split("");
        if (timeSplits.length != 4) {
            return false;
        }
        return true;
    }


}
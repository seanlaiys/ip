import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * Serves to take in the input keyed in by the user
 * and invokes methods in response to what the user has keyed in.
 *
 */
public class Parser {
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
                input = Messages.generateList(user_input, currentTasks);
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
                    String[] inputs = input.split("/");
                    Task newTask = new Deadline(inputs[0], inputs[1]);
                    currentTasks.addTasks(newTask);
                    input = Messages.sayAdd(user_input, newTask, currentTasks);
                    try {
                        currentStorage.appendTaskToFile(newTask);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (input.contains(Commands.COMMAND_EVENT)){
                String[] descriptions = input.split(Commands.COMMAND_EVENT);
                if (descriptions.length == 0) {
                    Messages.sayNoDescription();
                    input = user_input.nextLine();
                } else {
                    String[] inputs = input.split("/");
                    Task newTask = new Event(inputs[0], inputs[1]);
                    currentTasks.addTasks(newTask);
                    input = Messages.sayAdd(user_input, newTask, currentTasks);
                    try {
                        currentStorage.appendTaskToFile(newTask);
                    } catch (IOException e) {
                        e.printStackTrace();
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
            }else if (input.equals("")){
                Messages.sayEmpty();
                input = user_input.nextLine();
            } else {
                Messages.sayUnsure();
                input = user_input.nextLine();
            }
        }
        Messages.sayGoodbye();
    }
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

}

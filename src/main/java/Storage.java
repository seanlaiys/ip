import java.util.ArrayList;
/**
 *
 * This class serves to store the current tasks in an ArrayList.
 *
 */
public class Storage {
    private ArrayList<String> tasks;
    public Storage() {
        this.tasks = new ArrayList<String>();
    }
    public void addTasks(String input) {
        this.tasks.add(input);
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            list += i + ". "+ this.tasks.get(i-1) + "\n";
        }
        return list;
    }
}

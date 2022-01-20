import java.util.ArrayList;
/**
 *
 * This class serves to store the current tasks in an ArrayList.
 *
 */
public class Storage {
    private ArrayList<Task> tasks;
    public Storage() {
        this.tasks = new ArrayList<Task>();
    }
    public void addTasks(Task newTask) {
        this.tasks.add(newTask);
    }

    public void markTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber-1);
        selected.mark();
        this.tasks.set(taskNumber-1, selected);
    }

    public void unmarkTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber-1);
        selected.unmark();
        this.tasks.set(taskNumber-1, selected);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber-1);
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            list += i + "."+ this.tasks.get(i-1).toString() + "\n";
        }
        return list;
    }
}

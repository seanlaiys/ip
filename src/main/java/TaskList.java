import java.util.ArrayList;
/*
 *
 *  Stores task temporarily
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public void addTasks(Task newTask) {
        this.tasks.add(newTask);
    }

    public void removeTasks(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public void markTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.mark();
        this.tasks.set(taskNumber, selected);
    }

    public void unmarkTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.unmark();
        this.tasks.set(taskNumber, selected);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            list += i + "."+ this.tasks.get(i - 1).toString() + "\n";
        }
        return list;
    }
}

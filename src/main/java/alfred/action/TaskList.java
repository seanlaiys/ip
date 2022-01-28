package alfred.action;

import alfred.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 *  Encapsulates a list containing alfred.task.Task objects. <code>alfred.action.TaskList</code> contains
 *  <code>ArrayList<alfred.task.Task></code>
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new alfred.action.TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds alfred.task object into alfred.action.TaskList. Only a alfred.task.Task object can be passed into
     * the method, which will be stored in tasks.
     *
     * @param newTask the new alfred.task.Task object to be added
     *
     */
    public void addTasks(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Removes specified alfred.task object from alfred.action.TaskList.
     *
     * @param taskNumber the index of alfred.task.Task object to be removed
     *
     */
    public void removeTasks(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    /**
     * Marks specified alfred.task object from alfred.action.TaskList.
     *
     * @param taskNumber the index of alfred.task.Task object to be marked
     *
     */
    public void markTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.mark();
        this.tasks.set(taskNumber, selected);
    }

    /**
     * Unmarks specified alfred.task object from alfred.action.TaskList.
     *
     * @param taskNumber the index of alfred.task.Task object to be unmarked
     *
     */
    public void unmarkTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.unmark();
        this.tasks.set(taskNumber, selected);
    }

    /**
     * Returns specified alfred.task object from alfred.action.TaskList.
     *
     * @param taskNumber the index of alfred.task.Task object to be returned
     * @return the alfred.task.Task object specified by the index
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Returns alfred.action.TaskList containing tasks that fall on the specified date.
     *
     * @param date the date of the tasks
     * @return the new alfred.action.TaskList of the tasks that fall on a specified date
     */
    public TaskList getTasksByDate(LocalDate date) {
        TaskList newTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            if (current.getDate().equals(date)) {
                newTasks.addTasks(current);
            }
        }
        return newTasks;
    }

    /**
     * Returns the number of elements alfred.action.TaskList.
     *
     * @return the number of alfred.task.Task objects in alfred.action.TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of the alfred.action.TaskList object.
     *
     * @return the string listing the elements in alfred.action.TaskList
     */
    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            list += i + "."+ this.tasks.get(i - 1).toString() + "\n";
        }
        return list;
    }
}

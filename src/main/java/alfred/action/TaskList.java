package alfred.action;

import java.time.LocalDate;
import java.util.ArrayList;

import alfred.task.Task;

/**
 *  Encapsulates a list containing Task objects. TaskList contains
 *  ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds task object into TaskList. Only a Task object can be passed into
     * the method, which will be stored in tasks.
     *
     * @param newTask the new Task object to be added
     */
    public void addTasks(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Removes specified task object from TaskList.
     *
     * @param taskNumber the index of Task object to be removed
     */
    public void removeTasks(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    /**
     * Marks specified task object from TaskList.
     *
     * @param taskNumber the index of Task object to be marked
     */
    public void markTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.mark();
        this.tasks.set(taskNumber, selected);
    }

    /**
     * Unmarks specified task object from TaskList.
     *
     * @param taskNumber the index of Task object to be unmarked
     */
    public void unmarkTask(int taskNumber) {
        Task selected = this.tasks.get(taskNumber);
        selected.unmark();
        this.tasks.set(taskNumber, selected);
    }

    /**
     * Returns specified task object from TaskList.
     *
     * @param taskNumber the index of Task object to be returned
     * @return the Task object specified by the index
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Returns TaskList containing tasks that fall on the specified date.
     *
     * @param date the date of the tasks
     * @return the new TaskList of the tasks that fall on a specified date
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
     * Returns TaskList containing tasks that contain keyword input by user.
     *
     * @param keyword keyword input by user
     * @return the new TaskList of the tasks that has the given keyword
     */
    public TaskList getTasksByKeyWord(String keyword) {
        TaskList newTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            if (current.contains(keyword)) {
                newTasks.addTasks(current);
            }
        }
        return newTasks;
    }

    /**
     * Returns the number of elements TaskList.
     *
     * @return the number of Task objects in TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of the TaskList object.
     *
     * @return the string listing the elements in TaskList
     */
    @Override
    public String toString() {
        String list = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            list += i + "." + this.tasks.get(i - 1).toString() + "\n";
        }
        return list;
    }
}

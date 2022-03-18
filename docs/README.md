# User Guide
Alfred is a **desktop app for managing tasks**, optimized for use via a Command
Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you
can type fast, Alfred can get you managing your daily tasks faster than traditional GUI apps.
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a deadline](#adding-a-deadline-deadline): `deadline`
  - [Adding an event](#adding-an-event-event): `event`
  - [Adding a todo](#adding-a-todo-todo): `todo`
  - [Viewing the list of tasks](#viewing-tasks-list): `list`
  - [Finding a task](#finding-a-task-find): `find`
  - [Marking a task done](#marking-a-task-done-mark): `mark`
  - [Marking a task not done](#marking-a-task-not-done-unmark): `unmark`
  - [Removing a task](#removing-a-task-delete): `delete`
  - [Prioritising a task](#prioritising-a-task-priority): `priority`
  - [Exiting](#exiting-bye): `bye`
  - [Saving the data]()

## Quick Start
1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest Alfred Jar from [here](https://github.com/seanlaiys/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Alfred application.
4. Double-click the file to start the app.

## Features
### Note
Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo throw party`.

### Adding a Deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline NAME DATE`

Example: `deadline project /by 18/02/2022 2359`

### Adding an Event: `event`  

Adds an event to the task list.

Format: `event NAME DATE`

Example: `event throw party /at 19/02/2022 0100`

### Adding a Todo: `todo`

Adds a todo to the task list.

Format: `todo NAME`

Example: `todo read book`

### Viewing Tasks: `list`

Prints the list of the tasks as requested.

Formats: 
- `list`
- `list /on DATE`

Examples: 
- `list`
- `list /on 20/02/2022`

### Finding a Task: `find`

Prints the list of the tasks containing keyword input.

Format: `find NAME`

Example: `find book`

### Marking a Task done: `mark`

Marks a specified task on the tasklist as done.

Format: `mark TASK_NUMBER`

Example: `mark 1`

### Marking a Task not done: `unmark`

Marks a specified task on the tasklist as not done.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

### Removing a Task: `delete`

Removes a specified task on the tasklist.

Format: `delete TASK_NUMBER`

Example: `delete 1`

### Prioritising a Task: `priority`

Associates a task with different priority levels: `low`, `medium`, `high`

Format: `priority LEVEL TASK_NUMBER`

Example: `priority high 1`

### Exiting: `bye`

Exits the program.

Format: `bye`

### Saving the data

Alfred's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.



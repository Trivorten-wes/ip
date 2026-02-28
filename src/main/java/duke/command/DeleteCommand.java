package duke.command;

import duke.TaskList;
import duke.UI;

public class DeleteCommand implements Command {
    /**
     * Deletes the task from the list
     * @param description The task number to be deleted
     * @param tasks The list of task to remove from
     * @param ui UI used to print the deletion message
     */
    @Override
    public void execute(String description, TaskList tasks, UI ui) {
        int deleteTask = Integer.parseInt(description) - 1;
        ui.removeTask(tasks.get(deleteTask));
        tasks.delete(deleteTask);
    }
}

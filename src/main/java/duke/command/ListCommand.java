package duke.command;

import duke.TaskList;
import duke.UI;

public class ListCommand implements Command{
    /**
     * Lists out all the current tasks
     * @param tasks List of tasks to print
     * @param ui UI used for printing
     */
    public void execute(TaskList tasks, UI ui) {
        ui.list(tasks);
    }

    @Override
    public void execute(String description, TaskList tasks, UI ui) {
        ui.list(tasks);
    }
}



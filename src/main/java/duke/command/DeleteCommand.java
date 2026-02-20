package duke.command;

import duke.TaskList;
import duke.UI;

public class DeleteCommand implements Command{
    @Override
    public void execute(String description, TaskList tasks, UI ui) {
        int deleteTask = Integer.parseInt(description) - 1;
        ui.removeTask(tasks.get(deleteTask));
        tasks.delete(deleteTask);
    }
}

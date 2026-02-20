package duke.command;

import duke.TaskList;
import duke.UI;

public class ListCommand implements Command{

    public void execute(TaskList tasks, UI ui) {
        ui.list(tasks);
    }

    @Override
    public void execute(String description, TaskList tasks, UI ui) {
        ui.list(tasks);
    }
}



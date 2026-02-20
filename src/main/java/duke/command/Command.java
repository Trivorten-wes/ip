package duke.command;

import duke.TaskList;
import duke.UI;

public interface Command {
    public void execute(String description, TaskList tasks, UI ui);
}

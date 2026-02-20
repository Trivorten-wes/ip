package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesInvalidParameter;

public class MarkCommand implements Command {
    private boolean isDone;

    MarkCommand(boolean isDone) {
        this.isDone = isDone;
    }

    public void execute(String description, TaskList tasks, UI ui) throws HermesInvalidParameter {
        try {
            int index = Integer.parseInt(description) - 1;
            if (isDone) {
                tasks.mark(index);
                ui.mark(tasks.get(index));
            } else {
                tasks.unmark(index);
                ui.unmark(tasks.get(index));
            }
        } catch (NumberFormatException e) {
            throw new HermesInvalidParameter();
        }
    }
}

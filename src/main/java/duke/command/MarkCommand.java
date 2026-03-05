package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesInvalidParameter;

public class MarkCommand implements Command {
    private final boolean isDone;

    public MarkCommand(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Executes the mark command which
     * marks or unmark the tasks inputted as done
     * then calls the UI method to print to the
     * user an acknowledgment message
     * @param description String which contains information about
     *                    which task to mark
     * @param tasks the lists of tasks which contains the tasks to be marked
     * @param ui UI class that is used to print the message
     * @throws HermesInvalidParameter an error associated to a string that
     *                                is not a valid integer
     */
    public void execute(String description, TaskList tasks, UI ui)
            throws HermesInvalidParameter {
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

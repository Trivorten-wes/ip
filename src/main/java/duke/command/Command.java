package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesInvalidTime;

public interface Command {
    void execute(String description, TaskList tasks, UI ui) throws HermesMissingDescription, HermesInvalidTime;
}

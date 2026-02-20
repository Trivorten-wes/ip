package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesMissingTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.Todo;

public class AddCommand implements Command{
    private final TaskType taskType;

    public AddCommand(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public void execute(String description, TaskList tasks, UI ui) throws HermesMissingDescription, HermesMissingTime {
        switch (taskType) {
        case TODO:
            tasks.add(new Todo(description));
            ui.newTask(tasks.get(tasks.size() - 1), tasks.size() - 1);
            break;
        case DEADLINE:
            tasks.add(new Deadline(description));
            ui.newTask(tasks.get(tasks.size() - 1), tasks.size() - 1);
            break;
        case EVENT:
            tasks.add(new Event(description));
            ui.newTask(tasks.get(tasks.size() - 1), tasks.size() - 1);
            break;
        }
    }
}

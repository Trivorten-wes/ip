package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesMissingTime;

public class FindCommand implements Command {
    @Override
    public void execute(String description, TaskList tasks, UI ui) {
        String[] keywords = description.split("\\s+");
        TaskList matchingTasks = new TaskList();
        for (String keyword : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    matchingTasks.add(tasks.get(i));
                }
            }
        }
        ui.showFound(matchingTasks);
    }
}

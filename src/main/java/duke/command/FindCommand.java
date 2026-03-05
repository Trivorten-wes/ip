package duke.command;

import duke.TaskList;
import duke.UI;

public class FindCommand implements Command {
    /**
     * Prints out the list of tasks that
     * contain the keywords
     * @param description One or more keywords
     * @param tasks The list of tasks to search in
     * @param ui UI class used to print the list of tasks
     */
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

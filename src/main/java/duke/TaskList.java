package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void mark(int index) {
        tasks.get(index).setDone(true);
    }

    public void unmark(int index) {
        tasks.get(index).setDone(false);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}

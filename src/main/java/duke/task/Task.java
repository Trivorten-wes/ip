package duke.task;

/**
 * Class used to track the tasks the user
 * inputs into the list
 * isDone if the task is marked.
 * activity what the user needs to do.
 */

public abstract class Task {
    private boolean isDone;
    private String description;

    public Task() {
        isDone = false;
        description = null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String doneness;
        if (isDone) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        return doneness + description;
    }
}

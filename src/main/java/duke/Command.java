package duke;

public interface Command {
    public void execute(String description, TaskList tasks, UI ui);
}

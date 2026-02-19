package duke.task;

import duke.exceptions.HermesMissingDescription;

public class Todo extends Task {

    public Todo(String description, boolean isDone) throws HermesMissingDescription {
        if (description.isEmpty()) {
            throw new HermesMissingDescription();
        }
        setDescription(description);
        setDone(isDone);
    }

    public Todo(String description) throws HermesMissingDescription {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

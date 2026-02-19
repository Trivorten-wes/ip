package duke.task;

import duke.exceptions.HermesMissingTime;
import duke.exceptions.HermesMissingDescription;

public class Deadline extends Task {
    private String by;

    public void setBy(String date) {
        by = date;
    }
    public Deadline(String description, boolean isDone) throws HermesMissingTime, HermesMissingDescription {
        String[] components = description.split("by: ");
        if (components.length <= 1) {
            throw new HermesMissingTime();
        }
        if (components[0].isEmpty()) {
            throw new HermesMissingDescription();
        }
        setDescription(components[0]);
        setBy(components[1]);
        setDone(isDone);
    }

    public Deadline(String description) throws HermesMissingTime, HermesMissingDescription {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "by: " + by;
    }
}

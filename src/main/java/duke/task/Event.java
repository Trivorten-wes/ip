package duke.task;

import duke.exceptions.HermesMissingTime;
import duke.exceptions.HermesMissingDescription;

public class Event extends Task {
    private String start;
    private String end;

    public void setStart(String date) {
        start = date;
    }
    public void setEnd(String date) {
        end = date;
    }
    public Event(String description) throws HermesMissingTime, HermesMissingDescription {
        String[] components = description.split("/from |/to ");
        if (components.length <= 2) {
            throw new HermesMissingTime();
        }
        if (components[0].isEmpty()) {
            throw new HermesMissingDescription();
        }
        setDescription(components[0]);
        setStart(components[1]);
        setEnd(components[2]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start
                + " to: " + end +")";
    }

}

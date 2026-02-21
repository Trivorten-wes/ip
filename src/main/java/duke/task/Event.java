package duke.task;

import duke.exceptions.HermesInvalidTime;
import duke.exceptions.HermesMissingDescription;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public void setStart(LocalDateTime date) {
        start = date;
    }
    public void setEnd(LocalDateTime date) {
        end = date;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) throws HermesMissingDescription {
        setDescription(description);
        setStart(from);
        setEnd(to);
        setDone(isDone);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "from: " + start
                + " to: " + end;
    }

}

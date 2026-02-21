package duke.task;

import duke.exceptions.HermesInvalidTime;
import duke.exceptions.HermesMissingDescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        String fromDate;
        String toDate;
        if (start.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            fromDate = start.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            fromDate = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }

        if (end.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            toDate = end.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            toDate = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[E]" + super.toString() + "from: " + fromDate
                + " to: " + toDate;
    }

}

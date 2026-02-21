package duke.task;

import duke.exceptions.HermesMissingDescription;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public void setBy(LocalDateTime date) {
        by = date;
    }

    public Deadline(String description, LocalDateTime date, boolean isDone) throws HermesMissingDescription {
        setDescription(description);
    }

    @Override
    public String toString() {
        String byDate;
        if (by.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            byDate = by.toLocalDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            byDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[D]" + super.toString() + "by: " + byDate;
    }
}

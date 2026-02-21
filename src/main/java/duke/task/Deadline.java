package duke.task;

import duke.exceptions.HermesInvalidTime;
import duke.exceptions.HermesMissingDescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public void setBy(LocalDateTime date) {
        by = date;
    }

    public Deadline(String description, boolean isDone) throws HermesMissingDescription {
        setDescription(description);
    }

    public Deadline(String description) throws HermesMissingDescription {
        this(description, false);
    }

    public Deadline(String description, LocalDateTime date) {
        this(description);
        setBy(date);
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

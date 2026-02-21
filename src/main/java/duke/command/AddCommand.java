package duke.command;

import duke.TaskList;
import duke.UI;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesInvalidTime;
import duke.task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand implements Command{
    private final TaskType taskType;

    public AddCommand(TaskType taskType) {
        this.taskType = taskType;
    }

    public Task silentExecute(String description, boolean isDone, boolean isLoad) throws HermesInvalidTime{
        switch (taskType) {
        case TODO:
            return new Todo(description);
        case DEADLINE:
            String[] components = description.split("by:");
            return new Deadline(components[0], parseDateTime(components[1], isLoad), isDone);
        case EVENT:
            int fromIndex = description.indexOf("from:");
            int toIndex = description.indexOf("to:");
            LocalDateTime from;
            LocalDateTime to;
            String task;
            if (fromIndex == -1 || toIndex == -1) {
                throw new HermesInvalidTime();
            } else if (fromIndex < toIndex) {
                from = parseDateTime(description.substring(fromIndex + 5, toIndex), isLoad);
                to = parseDateTime(description.substring(toIndex + 3), isLoad);
                task = description.substring(0, fromIndex);
            } else {
                from = parseDateTime(description.substring(fromIndex + 5), isLoad);
                to = parseDateTime(description.substring(toIndex + 3, fromIndex), isLoad);
                task = description.substring(0, toIndex);
            }
            return new Event(task, from, to, isDone);
        }
        return null;
    }

    @Override
    public void execute(String description, TaskList tasks, UI ui) throws HermesMissingDescription, HermesInvalidTime {
        tasks.add(silentExecute(description, false, false));
        ui.newTask(tasks.get(tasks.size() - 1), tasks.size() - 1);
    }

    private LocalDateTime parseDateTime(String textInput, boolean isLoad) throws HermesInvalidTime{
        DateTimeFormatter dtFormatter;
        DateTimeFormatter dFormatter;
        if (isLoad) {
            dtFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            dFormatter  = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }

        try {
            return LocalDateTime.parse(textInput.strip(), dtFormatter);
        } catch (DateTimeParseException ignored) { }

        try {
            LocalDate date = LocalDate.parse(textInput.strip(), dFormatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new HermesInvalidTime();
        }
    }
}

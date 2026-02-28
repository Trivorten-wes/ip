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

public class AddCommand implements Command {
    private final TaskType taskType;

    public AddCommand(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns a task based on the information provided from
     * the inputs such as the task description and if it is done
     * @param description Details about the task to be added
     * @param isDone Whether the task should be added as done or not
     * @param isLoad Whether this method is being called normally
     *               or from loading from the file
     * @return Returns the task after extracting the relevant
     *          details from the string provided
     * @throws HermesMissingDescription Error if no task details is provided
     * @throws HermesInvalidTime Error if the time format is invalid
     */
    public Task silentExecute(String description, boolean isDone, boolean isLoad)
            throws HermesMissingDescription, HermesInvalidTime{
        switch (taskType) {
        case TODO:
            return new Todo(description);
        case DEADLINE:
            String[] components = description.split("by:");
            return new Deadline(components[0], parseDateTime(components[1], isLoad), isDone);
        case EVENT:
            Event task = createEvent(description, isLoad);
            if (isDone) {
                task.setDone(true);
            }
            return task;
        }
        return null;
    }

    /**
     * Returns an Event task from the text input
     * by parsing the start and end date from the string
     * @param description The string containing all the details
     * @param isLoad Whether the task is created from the loaded file
     * @return Returns the Event task created
     * @throws HermesInvalidTime Error if the date/time is in the wrong format
     * @throws HermesMissingDescription Error if some of the task details is missing
     */
    private Event createEvent(String description, boolean isLoad)
            throws HermesInvalidTime, HermesMissingDescription{
        // Look for the keywords from: and to:
        int fromIndex = description.indexOf("from:");
        int toIndex = description.indexOf("to:");

        // Variables to store values needed to create an event task
        LocalDateTime from;
        LocalDateTime to;
        String task;

        // Checks for the presence of the keywords
        // and if the user input the start date first or the end date
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
        return new Event(task, from, to, false);
    }

    /**
     * The method called whenever a command is added
     * which creates a task and add it to the task list
     * then calls the ui to print the "add task" message
     * @param description String containing the details about the task
     * @param tasks List of tasks to add to
     * @param ui UI used to print messages to the user
     * @throws HermesMissingDescription Error if task details are missing
     * @throws HermesInvalidTime Error if the date/time is in the wrong format
     */
    @Override
    public void execute(String description, TaskList tasks, UI ui)
            throws HermesMissingDescription, HermesInvalidTime {
        tasks.add(silentExecute(description, false, false));
        ui.newTask(tasks.get(tasks.size() - 1), tasks.size() - 1);
    }

    /**
     * Returns a LocalDateTime that is parsed from the
     * string input in a specific format.
     * The acceptable format of the inputted string depends on whether
     * it is user inputted or if it is from the files
     * @param textInput The string that is to be parsed
     * @param isLoad Whether it is from the files or user inputted
     * @return Returns in a proper LocalDateTime format
     * @throws HermesInvalidTime Error if the string is not in proper format
     */
    private LocalDateTime parseDateTime(String textInput, boolean isLoad) throws HermesInvalidTime{
        DateTimeFormatter dtFormatter;
        DateTimeFormatter dFormatter;

        // If loaded from the files it would be in a different format
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

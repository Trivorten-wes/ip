package duke;

import duke.command.*;
import duke.exceptions.HermesMissingDescription;
import duke.exceptions.HermesMissingTime;
import duke.task.TaskType;

import java.io.IOException;

public class Parser {

    public CommandWord parseCommand(String commandWord) {
        return CommandWord.valueOf(commandWord.toUpperCase());
    }

    public Command parse(String inputText) {
        CommandWord commandWord = parseCommand(inputText);
        return switch (commandWord) {
            case LIST -> new ListCommand();
            case MARK -> new MarkCommand(true);
            case UNMARK -> new MarkCommand(false);
            case DELETE -> new DeleteCommand();
            case TODO -> new AddCommand(TaskType.TODO);
            case DEADLINE -> new AddCommand(TaskType.DEADLINE);
            case EVENT -> new AddCommand(TaskType.EVENT);
        };
    }
}

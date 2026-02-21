package duke;

import duke.command.*;
import duke.task.TaskType;

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

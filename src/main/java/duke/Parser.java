package duke;

import duke.command.*;
import duke.exceptions.HermesInvalidTime;
import duke.task.TaskType;

public class Parser {

    public CommandWord parseCommand(String commandWord) {
        return CommandWord.valueOf(commandWord.toUpperCase());
    }

    /**
     * Parses the given text input to find
     * which command is being called
     * then returns the class corresponding
     * with the command with the correct fields
     * @param inputText the string from the user input
     * @return the class associated with the command
     *          that was parsed from the input text
     */
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
            case FIND -> new FindCommand();
        };
    }
}
